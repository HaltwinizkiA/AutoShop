package autoShop.daoImpl.hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.internal.StandardServiceRegistryImpl;

public class HibernateAutoShop {
  private static final   SessionFactory sessionFactory = configureSessionFactory();
  private static HibernateAutoShop hibernateAutoShop;

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static HibernateAutoShop getIstance() {
    if ( hibernateAutoShop == null) {
      hibernateAutoShop = new HibernateAutoShop();
    }
    return hibernateAutoShop;
}

  private static SessionFactory configureSessionFactory() {
    Configuration configuration=new Configuration();
    configuration.configure();
    ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
    return configuration.buildSessionFactory(serviceRegistry);
  }

}
