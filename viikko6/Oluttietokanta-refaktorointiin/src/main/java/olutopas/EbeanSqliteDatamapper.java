package olutopas;

    import com.avaje.ebean.EbeanServer;
    import com.avaje.ebean.EbeanServerFactory;
    import com.avaje.ebean.Transaction;
    import com.avaje.ebean.config.DataSourceConfig;
    import com.avaje.ebean.config.ServerConfig;
    import com.avaje.ebean.config.dbplatform.SQLitePlatform;
    import java.util.List;
    import olutopas.model.User;

    public class EbeanSqliteDatamapper implements Datamapper {
     
        private EbeanServer server;
        private String tietokantaURL;
        private User user;
       
        public EbeanSqliteDatamapper(String tietokantaURL, boolean dropAndCreateTables, Class... luokat) {
            this.tietokantaURL = tietokantaURL;
            this.server = initializeDatabase(dropAndCreateTables, luokat);
        }
       
       
        private EbeanServer initializeDatabase(boolean dropAndCreateDatabase, Class... luokat) {
            ServerConfig config = new ServerConfig();
            config.setName("beerDb");
     
            DataSourceConfig sqLite = new DataSourceConfig();
            sqLite.setDriver("org.sqlite.JDBC");
            sqLite.setUsername("mluukkai");
            sqLite.setPassword("mluukkai");
            sqLite.setUrl(tietokantaURL);
            config.setDataSourceConfig(sqLite);
            config.setDatabasePlatform(new SQLitePlatform());
            config.getDataSourceConfig().setIsolationLevel(Transaction.READ_UNCOMMITTED);
     
            config.setDefaultServer(false);
            config.setRegister(false);
     
            for (Class luokka : luokat) {
                    config.addClass(luokka);
            }
     
            if (dropAndCreateDatabase) {
                config.setDdlGenerate(true);
                config.setDdlRun(true);
                //config.setDebugSql(true);
            }
            return EbeanServerFactory.create(config);
        }
       
       
        @Override
        public EbeanServer getServer() {
            return this.server;
        }
     
       
        @Override
        public void setUser(User user) {
            this.user = user;
        }
       
        @Override
        public User getUser() {
            return user;
        }
       
        @Override
        public Object findName(Class c, String name) {
            return getServer().find(c).where().like("name", name).findUnique();
        }
       
        @Override
        public void save(Object o) {
            getServer().save(o);
        }
       
        @Override
        public List getList(Class c) {
            return server.find(c).findList();
        }
     
        @Override
        public Object findID(Class c, int ID) {
            return server.find(c, ID);
        }
    }

