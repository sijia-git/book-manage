package book.modle;

public class Lend {
	private String sernum;
	private String book_id;
	private String reader_id;
	private String lend_date;
	private String back_date;	
	//无参构造方法
	public Lend() {
		super();

	}
	//全参构造方法
	public Lend( String sernum, String book_id, String reader_id, String lend_date,String back_date) {
		super();
		this.book_id = book_id;
		this.sernum = sernum;
		this.reader_id = reader_id;
		this.lend_date = lend_date;
		this.back_date = back_date;
		}
		public String getBook_id() {
			return book_id;
		}
		public void setBook_id(String book_id) {
			this.book_id = book_id;
		}
		public String getSernum() {
			return sernum;
		}
		public void setSernum(String sernum) {
			this.sernum = sernum;
		}
		public String getReader_id() {
			return reader_id;
		}
		public void setReader_id(String reader_id) {
			this.reader_id = reader_id;
		}
		public String getLend_date() {
			return lend_date;
		}
		public void setLend_date(String lend_date) {
			this.lend_date = lend_date;
		}
		public String getBack_date() {
			return back_date;
		}
		public void setBack_date(String back_date) {
			this.back_date = back_date;
		}
		
}
		
