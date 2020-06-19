package book.modle;

public class Booklend {
	private String book_id;
	private String name;
	private String author;
	private String publish;
	private String introduction;
	private String state;
	//无参构造方法
	public Booklend() {
		super();

	}
	//全参构造方法
	public Booklend(String book_id, String name, String author, String publish,String introduction,String state) {
		super();
		this.book_id = book_id;
		this.name = name;
		this.author = author;
		this.publish = publish;
		this.introduction = introduction;
		this.state = state;
		}
		public String getBook_id() {
			return book_id;
		}
		public void setBook_id(String book_id) {
			this.book_id = book_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getPublish() {
			return publish;
		}
		public void setPublish(String publish) {
			this.publish = publish;
		}
		public String getIntroduction() {
			return introduction;
		}
		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
}
		
