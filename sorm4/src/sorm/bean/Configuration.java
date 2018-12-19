package sorm.bean;

/**
 * 管理配置信息
 * @author sxj
 *
 */
public class Configuration {
	private String driver;
	private String url;
	private String user;
	private String pwd;
	private String usingDB; //正在使用哪个数据库
	private String srcPath; //项目的源码路径
	private String poPackage; //扫描生成java类的包（【po的意思是:Persistence object持久化对象）
	private String queryFactory; //
	
	public Configuration(String driver, String url, String user, String pwd,
			String usingDB, String srcPath, String poPackage, String queryFactory) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.usingDB = usingDB;
		this.srcPath = srcPath;
		this.poPackage = poPackage;
		this.queryFactory = queryFactory;
	}
	
	public Configuration() {
		// TODO Auto-generated constructor stub
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsingDB() {
		return usingDB;
	}

	public void setUsingDB(String usingDB) {
		this.usingDB = usingDB;
	}

	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	public String getPoPackage() {
		return poPackage;
	}

	public void setPoPackage(String poPackage) {
		this.poPackage = poPackage;
	}

	public String getQueryFactory() {
		return queryFactory;
	}

	public void setQueryFactory(String queryFactory) {
		this.queryFactory = queryFactory;
	}
}
