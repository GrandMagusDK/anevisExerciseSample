package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.PieChartData;

public class PieChartDataDAO extends BaseDAO{

	public void save(PieChartData data) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(data);
		tx.commit();
		session.close();
	}
	
	public void saveList(List<PieChartData> dataList) {
		for(PieChartData entry : dataList) {
			save(entry);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PieChartData> list() {
		Session session = this.sessionFactory.openSession();
		List<PieChartData> dataList = session.createQuery("from PieChartData").list();
		session.close();
		return dataList;
	}
	
	public void clearTable() 
	{
		Session session = this.sessionFactory.openSession();
		session.createQuery("delete from PieChartData").executeUpdate();
		session.close();
	}

}
