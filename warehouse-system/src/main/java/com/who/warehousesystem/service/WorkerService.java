package com.who.warehousesystem.service;

import com.who.warehousesystem.dto.WorkerSaveDto;
import com.who.warehousesystem.model.User;
import com.who.warehousesystem.model.Worker;
import com.who.warehousesystem.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    UserService userService;

    public List<Worker> findAllWorkersDgv() {
        return workerRepository.findAllWorkersDgv().orElse(new ArrayList<>());
    }

    public Worker saveWorker(WorkerSaveDto dto, Integer userId) throws Exception {
        checkDuplicates(dto);
        User user = userService.findUserById(userId);
        Worker worker = new Worker(dto,user);
        return workerRepository.save(worker);
    }

    private void checkDuplicates(WorkerSaveDto dto) throws Exception {
        if(workerRepository.findWorkerByNamesAndMobile(dto.getEnName(),dto.getArName(),dto.getMobile()) != null)
            throwDuplicateException();
    }

    private void throwDuplicateException() throws Exception {
        throw new Exception("There is another worker with same name and mobile");
    }

    public Worker editWorker(Integer id, WorkerSaveDto dto, Integer userId) throws Exception {
        checkDuplicates(id,dto);
        User user = userService.findUserById(userId);
        Worker worker = findWorkerById(id);
        worker.setArName(dto.getArName());
        worker.setEnName(dto.getEnName());
        worker.setMobile(dto.getMobile());
        worker.setUpdatedBy(user);
        return workerRepository.save(worker);
    }

    private Worker findWorkerById(Integer id) throws Exception {
        return workerRepository.findWorkerById(id).orElseThrow(() ->
                new Exception("Worker not found for ID : " + id));
    }

    private void checkDuplicates(Integer id, WorkerSaveDto dto) throws Exception {
        Worker worker = workerRepository.findWorkerByNamesAndMobile(dto.getEnName(),dto.getArName(),
                dto.getMobile());
        if(worker != null && worker.getId() != id)
            throwDuplicateException();
    }

    public void deleteWorker(Integer id, Integer userId) throws Exception {
        checkRelatedData(id);
        User user = userService.findUserById(userId);
        Worker worker = findWorkerById(id);
        worker.setActive(false);
        worker.setUpdatedBy(user);
        workerRepository.save(worker);
    }

    @Autowired
    CheckWorkerService checkWorkerService;

    private void checkRelatedData(Integer workerId) throws Exception {
        if(checkWorkerService.findCheckWorkersByWorker(workerId).size() > 0)
            throw new Exception("This worker cannot be deleted because it's included in another tables");
    }

    @Autowired
    EntityManager entityManager;

    public List<Worker> searchWorkers(String enName, String arName, String mobile) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Worker> cq = cb.createQuery(Worker.class);
        Root<Worker> root = cq.from(Worker.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("active"), true));

        if(!enName.isEmpty() && !enName.isBlank())
            predicates.add(cb.like(root.get("enName"), "%" + enName + "%"));
        if(!arName.isEmpty() && !arName.isBlank())
            predicates.add(cb.like(root.get("arName"), "%" + arName + "%"));
        if (!mobile.isBlank() && !mobile.isEmpty())
            predicates.add(cb.equal(root.get("mobile"), mobile));

        cq.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<String> findAllWorkersByCheck(Integer checkId) {
        return workerRepository.findAllWorkersNotInCheck(checkId).orElse(new ArrayList<>()).stream().map(
                worker -> {
                    return worker.getId() + " - " + worker.getEnName();
                }
        ).collect(Collectors.toList());
    }

    public List<Worker> extractWorkerFromStringList(List<String> workersList) throws Exception {
        List<Worker> workers = new ArrayList<>();
        for(String s : workersList) {
            String id = s.substring(0,s.indexOf(' '));
            Worker worker = findWorkerById(Integer.parseInt(id));
            workers.add(worker);
        }
        return workers;
    }
}
