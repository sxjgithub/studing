package demo3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*
 * 封装响应
 */
public class Response {
	public static final String CRLF = "\r\n" ;
	public static final String BLANK = " " ;
	
	private StringBuilder headInfo ; //存储响应头信息
	private int len ; //响应正文的长度
	private StringBuilder headContent ;
	
	private BufferedWriter bw ;
	
	private Response() {
		this.headInfo = new StringBuilder() ; 
		this.len = 0 ;
		this.headContent = new StringBuilder() ;
	}
	
	public Response(OutputStream os){
		this() ;
		this.bw = new BufferedWriter(new OutputStreamWriter(os)) ;
	}
	public Response(Socket c_socket) throws IOException{
		this() ;
		this.bw = new BufferedWriter(new OutputStreamWriter(c_socket.getOutputStream())) ;
	}
	
	public void pushToClient(int code) throws IOException{
		this.createHeadInfo(code);
		bw.append(this.headInfo.toString()) ;
		bw.append(this.headContent.toString()) ;
		bw.flush();
		bw.close();
	}
//构建正文
	public Response print(String info){
		this.headContent.append(info) ;
		this.len = info.getBytes().length ;
		return this ;
	}
	public Response println(String info){
		this.headContent.append(info) ;
		this.headContent.append(CRLF) ;
		this.len = (info + CRLF).getBytes().length ;
		return this ;
	}
	private void createHeadInfo(int code){
		//添加HTTP协议版本、状态码、描述
		this.headInfo.append("HTTP/1.1").append(BLANK).append(String.valueOf(code)).append(BLANK) ;
		switch(code){
			case 200:
				this.headInfo.append("OK");
				break;
			case 404:
				this.headInfo.append("NOT FOUND");
				break;
			case 505:
				this.headInfo.append("SERVER ERROR");
				break;
		}
		this.headInfo.append(CRLF) ;
		//添加响应头（Response Head）
		this.headInfo.append("Server:sxj Server").append(CRLF) ;
		this.headInfo.append("Content-type:text/html;charset= GBK").append(CRLF) ;//响应文本：非常重要
		this.headInfo.append("Content-Length:")//响应正文长度：非常重要
			.append(this.len).append(CRLF) ;
		//添加crlf
		this.headInfo.append(CRLF) ;

	}
}
