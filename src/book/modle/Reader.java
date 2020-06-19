package book.modle;

public class Reader {
	private int reader_id;//读者ID
	private String name;//读者姓名
	private String sex;//读者性别
	private String birth;//出生日期
	private String address;//户籍
	private String telcode;//电话
	//无参构造方法
	public Reader() {
		super();

	}
	//全参构造方法
	public Reader(int reader_id, String name, String sex, String birth,String address,String telcode) {
		super();
		this.reader_id = reader_id;
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.address = address;
		this.telcode = telcode;
		}
		public int getReader_id() {
			return reader_id;
		}
		public void setReader_id(int reader_id) {
			this.reader_id=reader_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getBirth() {
			return birth;
		}
		public void setBirth(String birth) {
			this.birth = birth;
		}
		public String getAddress() {
			return address;
		}
		public void setAdress(String address) {
			this.address = address;
		}
		public String getTelcode() {
			return telcode;
		}
		public void setTelcode(String telcode) {
			this.telcode = telcode;
		}
		
}
		
