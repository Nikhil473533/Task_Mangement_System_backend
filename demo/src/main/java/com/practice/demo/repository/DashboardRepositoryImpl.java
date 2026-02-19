package com.practice.demo.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.practice.demo.beans.Task;
import com.practice.demo.dto.StatusCountDTO;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository{

	@PersistenceContext
	EntityManager em;
	
 public long getCountOfAllActiveTasks(){
	 
	 String sql = " SELECT COUNT(*) AS active_task_count "
	 		     + "FROM task t "
	 		     + "WHERE t.deleted = 0";
	 Object obj = em.createNativeQuery(sql).getSingleResult();

	return ((Number)obj).longValue();
 }

public List<StatusCountDTO> getStatusWiseTaskCount(){
    String jpql =
            "SELECT new com.practice.demo.dto.StatusCountDTO(" +
            "ts.code, ts.displayName, COUNT(t.id)) " +
            "FROM TaskStatus ts " +
            "LEFT JOIN Task t " +
            "ON t.taskStatus = ts " +
            "AND t.deleted = false " +
            "GROUP BY ts.id, ts.code, ts.displayName " +
            "ORDER BY ts.id";

 return em.createQuery(jpql, StatusCountDTO.class).getResultList();
 
}

 
public List<Task> getMyActiveTasks(Long userId){
	
	String jpql = "SELECT t FROM Task t "+
	              "LEFT JOIN FETCH t.taskStatus "+
			      "WHERE t.assignedUser.id =:userId "+
			      "AND t.deleted = false"; 
	
	return em.createQuery(jpql, Task.class)
	         .setParameter("userId", userId)
	         .getResultList();
}

}
