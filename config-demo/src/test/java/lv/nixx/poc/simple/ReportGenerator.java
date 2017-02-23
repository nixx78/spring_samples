package lv.nixx.poc.simple;

public class ReportGenerator {

	public String create() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(addHeaderAndStyle() + "\n");
		sb.append(addTableHeader() + "\n");
		sb.append(addTestSuiteInfo() + "\n");
		
		sb.append(addTestCase()+ "\n");
		sb.append(addTestCase()+ "\n");
		
		sb.append(addFooter());
		
		return sb.toString();
	}
	
	private String addHeaderAndStyle() {
		return "<!DOCTYPE html><html><body><style type=\"text/css\">"
				+ ".tg  {border-collapse:collapse;border-spacing:0;}"
				+ ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}"
				+ ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}"
				+ ".tg .test-suite-header{font-weight:bold;background-color:#9aff99;text-align:center;vertical-align:top}"
				+ ".tg .tg-baqh{text-align:center;vertical-align:top}"
				+ ".tg .field-header{font-weight:bold;background-color:#FBFFA8;vertical-align:top}"
				+ ".tg .tg-dx8v{font-size:12px;vertical-align:top}"
				+ ".tg .tg-u8fl{font-weight:bold;background-color:#9aff99;vertical-align:top}"
				+ ".tg .tg-a080{background-color:#9aff99;vertical-align:top}"
				+ ".tg .frow-ok{background-color:#DCFCE4;vertical-align:top}"
				+ ".tg .frow-fail{background-color:#FFE2AB;vertical-align:top}"
			+ "</style>";
	}
	
	private String addTableHeader() {
		return 
		"<table class=\"tg\" style=\"undefined; table-layout: fixed; width: 1133px\">" +
		addCollGroup(187,279,249,201);
	}
	
	private Object addFooter() {
		return "</table></body></html>";
	}

	
	private String addTestSuiteInfo() {
		return 
		 "<tr>" +    
		    "<th class=\"test-suite-header\" colspan=\"2\">Test suite<br></th>" +
		    "<th class=\"tg-baqh\" colspan=\"3\" rowspan=\"5\">Description</th>" +
		  "</tr>" +
		  "<tr>" +
		  	addRows("tg-dx8v", "Result", "Success") +
		  "</tr>" +
		  "<tr>" +
		  	addRows("tg-dx8v", "ProcessingTime", "919199") +
		  "</tr>";
	}
	
	
	private String addTestCaseHeader() {
		return 
		  "<tr>" +
		    "<td class=\"tg-u8fl\">Test Case: Name<br></td>" +
		    "<td class=\"tg-u8fl\">Description<br></td>" +
		  "</tr>" +
		  "<tr>" +
		  	addRows("tg-a080", "Result", "Success") +
		  "</tr>" + 
		  "<tr>" +
			addRows("field-header", "Name", "Actual", "Expected", "Diff", "Result") + 
		  "</tr>";
	}

	private String addTestCase() {
		StringBuilder sb = new StringBuilder();
		sb.append(addTestCaseHeader());
		sb.append(addFieldRow("name1", true));
		sb.append(addFieldRow("name2", false));
		sb.append(addFieldRow("name3", true));
		return sb.toString();
	}
	
	private String addFieldRow(String name, boolean isValid) {
		return 
		 "<tr>" +
			addRows(isValid ? "frow-ok" : "frow-fail", name, "ActualValue", "ExpectedValue", 10, isValid) +
		 "</tr>";
	}
	
	private String addRows(String style, Object... values) {
		StringBuilder sb = new StringBuilder();
		for (Object v: values) {
			sb.append("<td class=\"" + style+ "\">" + v + "</td>");
		}
		return sb.toString();
	}
	
	private String addCollGroup(int... widths) {
		StringBuilder sb = new StringBuilder();
		sb.append("<colgroup>");
		for(int w: widths) {
			sb.append("<col style=\"width: " + w + "px\">");
		}
		sb.append("</colgroup>");		
		return sb.toString();
	}

	


}
