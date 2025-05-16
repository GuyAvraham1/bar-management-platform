package com.guyavraham.barmanagement.util;

public class HtmlBuilder {

    public static String getNavBar(boolean isAuthenticated) {
        StringBuilder html = new StringBuilder();
        html.append("<div style='background-color: #333; overflow: hidden;'>");
        html.append("<a style='float: left; color: white; text-align: center; padding: 14px 16px; text-decoration: none;' href='/api/hello'>Home</a>");

        if (isAuthenticated) {
            html.append("<a style='float: left; color: white; text-align: center; padding: 14px 16px; text-decoration: none;' href='/api/authenticated'>Dashboard</a>");
            html.append("<form style='float: right;' action='/logout' method='post'>");
            html.append("<button style='background-color: red; color: white; padding: 14px 16px; border: none; cursor: pointer;' type='submit'>Logout</button>");
            html.append("</form>");
        } else {
            html.append("<a style='float: right; color: white; text-align: center; padding: 14px 16px; text-decoration: none;' href='/login'>Login</a>");
        }

        html.append("</div><br>");
        return html.toString();
    }

    public static String wrapHtml(String title, String content, boolean isAuthenticated) {
        return "<html><head><title>" + title + "</title></head><body>" +
               getNavBar(isAuthenticated) +
               content +
               "</body></html>";
    }
}