/*
 * This source code is obtained from 
 * http://gaofeihang.blog.163.com/blog/static/84508285201032305624390/
 * 
 * Added html title & comment remover by ISL
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class htmlRemover {
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html_title = "<title>.*</title>"; // 定义HTML标签的正则表达式
    private static final String regEx_html_subtitle = "<h1>.*</h1>"; // 定义HTML标签的正则表达式
    private static final String regEx_html_comment ="<ul.*</ul>";
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
 
    

    public static String delHTMLTag(String htmlStr) {
		
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签


        Pattern p_html_title = Pattern.compile(regEx_html_title, Pattern.CASE_INSENSITIVE);
        Matcher m_html_title = p_html_title.matcher(htmlStr);
        htmlStr = m_html_title.replaceAll(""); // Remove title
        
        Pattern p_html_comment = Pattern.compile(regEx_html_comment, Pattern.CASE_INSENSITIVE);
        Matcher m_html_comment = p_html_comment.matcher(htmlStr);
        htmlStr = m_html_comment.replaceAll(""); // Remove comment
        
        Pattern p_html_subtitle = Pattern.compile(regEx_html_subtitle, Pattern.CASE_INSENSITIVE);
        Matcher m_html_subtitle = p_html_subtitle.matcher(htmlStr);
        htmlStr = m_html_subtitle.replaceAll(""); // Remove comment

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        
        return htmlStr.trim(); // 返回文本字符串
    }
}
