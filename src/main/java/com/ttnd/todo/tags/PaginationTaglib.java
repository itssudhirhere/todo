package com.ttnd.todo.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTaglib extends SimpleTagSupport {
	private String uri;
	private int offset;
	private int count;
	private int max = 10;
	private int steps = 10;
	private String previous = "Previous";
	private String next = "Next";
	private int constant = 100;
	private int pageSize = 100;//100;
	private int previousPageNo;// = pageSize-constant;//100;
	private int itr=0;
	
	private Writer getWriter() {
		JspWriter out = getJspContext().getOut();
		return out;
	}
	
	@Override
	public void doTag() throws JspException {
		Writer out = getWriter();
		if(offset%constant==0){
			if(offset>=constant){
				pageSize = (offset/constant +1)*constant;
				itr = offset;
				previousPageNo = offset-constant;
				
			}else{
					
			}
		}
		else{
			
			int cocent = offset/constant;
			//int reminder = offset%100;
			pageSize = cocent*constant + constant;
			itr = cocent*constant;
			if((cocent*constant -constant)>0)
				previousPageNo = cocent*constant -constant;	
			
		}
		if(pageSize>count){
			pageSize=count;
		}
		
		try {
			out.write("<div class=\"pagination\">");
			out.write("<nav>");
			out.write("<ul>");
						
			if(offset<=previousPageNo){
				out.write(constructLink(1, previous, "disabled", true));
			}
			else{
				out.write(constructLink(previousPageNo, previous, null, false));
			}
			for(;itr<pageSize;itr+=steps) {
			
				if(offset==itr)
					out.write(constructLink((itr/steps+1)-1 *steps, String.valueOf(itr/steps+1), "active", true));
				else
					out.write(constructLink(itr/steps*steps, String.valueOf(itr/steps+1), null , false));
			}

			if(pageSize==count)
				out.write(constructLink(offset, next, "disabled", true));
			else
				out.write(constructLink(pageSize, next, null , false));
			
			
			out.write("</ul>");
			out.write("</nav>");
			out.write("</div>");
		} catch (java.io.IOException ex) {
			throw new JspException("Error in Paginator tag", ex);
		}
	}


	private String constructLink(int page, String text, String className, boolean disabled) {
		StringBuilder link = new StringBuilder("<li");
		if (className != null) {
			link.append(" class=\"");
			link.append(className);
			link.append("\"");
		}
		if(disabled)
			link.append(">").append("<a href=\"#\">"+text+"</a></li>");
		else
			link.append(">").append("<a href=\""+uri+"?offset="+page +"&maxResults="+max+ "\">"+text+"</a></li>");
		return link.toString();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		steps = max;
		constant =  max*10;
		pageSize = max*10;
		previousPageNo = pageSize-constant;
		this.max = max;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public int getSteps() {
		return steps;
	}

	private void setSteps(int steps) {
		this.steps = steps;
	}
	private void setConstant(int constant) {
		this.constant = constant;
	}

}
