package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.RingChartData;

public class RingChartDataDAO extends BaseDAO{

	public void save(RingChartData data) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(data);
		tx.commit();
		session.close();
	}
	
	public void saveList(List<RingChartData> dataList) {
		for(RingChartData entry : dataList) {
			save(entry);
		}
	}

	@SuppressWarnings("unchecked")
	public List<RingChartData> list() {
		Session session = this.sessionFactory.openSession();
		List<RingChartData> dataList = session.createQuery("from RingChartData").list();
		session.close();
		return dataList;
	}
	
	public void clearTable() 
	{
		Session session = this.sessionFactory.openSession();
		session.createQuery("delete from RingChartData").executeUpdate();
		session.close();
	}

}
