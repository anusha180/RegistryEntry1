/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl.esi.web;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

 
/**
 * Servlet implementation class MySQLConnect
 */
 @WebServlet("/UpdaServlet")
public class UpdateRegistryEntryServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String scope = request.getParameter("scope");
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/re", "root", "root");
            PreparedStatement pst = conn.prepareStatement("update registry_entry set scope=?, name=?, value=? where reid=?");
            pst.setString(1, scope);
            pst.setString(2, name);
            pst.setString(3, value);
            pst.setString(4, id);
            
             int i=pst.executeUpdate(); 
            if (i==0) {
                out.println("\n" +
"<!DOCTYPE html>\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\" />\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
"        <title>Free Bootstrap Admin Template : Binary Admin</title>\n" +
"        <script type=\"text/javascript\" src=\"js/jquery-1.12.4.min.js\"></script>\n" +
"<script type=\"text/javascript\" src=\"js/bootstrap.min.js\"></script>\n" +
"<script type=\"text/javascript\" src=\"js/registry.js\"></script>\n" +
"<!--  --link href=\"css/registry.css\" rel=\"stylesheet\"--!>\n" +
"<!-- Bootstrap -->\n" +
"<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">\n" +
"<link href=\"css/bootstrap-theme.css\" rel=\"stylesheet\" media=\"screen\">\n" +
"<link href=\"css/bootstrap-theme.min.css\" rel=\"stylesheet\" media=\"screen\">\n" +
"        <!-- BOOTSTRAP STYLES-->\n" +
"        <link href=\"css/bootstrap.css\" rel=\"stylesheet\" />\n" +
"        <!-- FONTAWESOME STYLES-->\n" +
"        <link href=\"css/font-awesome.css\" rel=\"stylesheet\" />\n" +
"        <!-- CUSTOM STYLES-->\n" +
"        <link href=\"css/custom.css\" rel=\"stylesheet\" />\n" +
"        <!-- GOOGLE FONTS-->\n" +
"        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />\n" +
"    </head>\n" +
"    <body>\n" +
"        <div id=\"wrapper\">\n" +
"            <nav class=\"navbar navbar-default navbar-cls-top \" role=\"navigation\" style=\"margin-bottom: 0\">\n" +
"                <div class=\"navbar-header\">\n" +
"                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".sidebar-collapse\">\n" +
"                        <span class=\"sr-only\">Toggle navigation</span>\n" +
"                        <span class=\"icon-bar\"></span>\n" +
"                        <span class=\"icon-bar\"></span>\n" +
"                        <span class=\"icon-bar\"></span>\n" +
"                    </button>\n" +
"\n" +
"                </div>\n" +
"                <div style=\"color: white;\n" +
"                     padding: 15px 50px 5px 50px;\n" +
"                     float: right;\n" +
"                     font-size: 16px;\"> </div>\n" +
"            </nav>   \n" +
"            <!-- /. NAV TOP  -->\n" +
"            <nav class=\"navbar-default navbar-side\" role=\"navigation\">\n" +
"                <div class=\"sidebar-collapse\">\n" +
"                    <ul class=\"nav\" id=\"main-menu\">\n" +
"                        <li class=\"text-center\">\n" +
"                            <img src=\"img/find_user.png\" class=\"user-image img-responsive\"/>\n" +
"                        </li>\n" +
"                        \n" +
"                        <li>\n" +
"                            <a id=\"anchAddEntry\"  href=\"#\"  ><i class=\"fa fa-plus-square fa-3x\"></i> Add Entry</a>\n" +
"                        </li>\n" +
"\n" +
"                        <li>\n" +
"                            <a id=\"anchSearchEntry\"  href=\"#\"><i class=\"fa fa-search fa-3x\"></i> Search Entry</a>\n" +
"                        </li>\n" +
"                        \n" +
"                        <li>\n" +
"                            <a id=\"anchUpdateEntry\"  href=\"#\"><i class=\"fa fa-pencil-square-o fa-3x\"></i> Update Entry</a>\n" +
"                        </li>\n" +
"                        <li  >\n" +
"                            <a id=\"anchDeleteEntry\"  href=\"#\"><i class=\"fa fa-trash-o fa-3x\"></i> Delete Entry</a>\n" +
"                        </li>	\n" +
"\n" +
"                    </ul>\n" +
"\n" +
"                </div>\n" +
"\n" +
"            </nav>  \n" +
"            <!-- /. NAV SIDE  -->\n" +
"            <div id=\"page-wrapper\" >\n" +
"                <div id=\"page-inner\">\n" +
"                    <div class=\"row\">\n" +
"\n" +
"                    </div>\n" +
"\n" +
"                    <hr />\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-3\"></div>\n" +
"                        <div class=\"col-md-6\">\n" +
"                            <!-- Form Elements -->\n" +
"                            <div class=\"panel panel-default\">\n" +
"                                <div class=\"panel-heading\">\n" +
"                                    <h3 id=\"htagAdd\">Add Registry Entry </h3>\n" +
"                                    <h3 id=\"htagSearch\">Search Registry Entry </h3>\n" +
"                                    <h3 id =\"htagUpdate\">Update Registry</h3>\n" +
"                                    <h3 id =\"htagDelete\">Delete Registry</h3>\n" +
"                                </div>\n" +
"                                <div id=\"dvAdd\"  class=\"panel-body\">\n" +
"                                    \n" +
"                                    <form role=\"form\">\n" +
"                                        <div id=\"regentrymsg1\"></div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Scope</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryscope\" type=\"text\"/>\n" +
"\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Name</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryname\" type=\"text\" placeholder=\"Example:com.express_scripts.services\" />\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Value</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryvalue\" type=\"text\" placeholder=\"Example:ref_product_app_dev1\"/>\n" +
"                                        </div>\n" +
"\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" value=\"\" id=\"regentryconfid\">Confidential\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"\n" +
"                                        <br/>\n" +
"                                        <button class=\"btn btn-default\" type=\"button\" id=\"regentryaddbtn\">Add</button>\n" +
"                                        <button  class=\"btn btn-default\">Cancel</button>\n" +
"                                        \n" +
"\n" +
"                                    </form>                            \n" +
"                                </div>\n" +
"                                <div  id=\"dvSearch\" class=\"panel-body\">\n" +
"                                    \n" +
"                                    <form role=\"form\">\n" +
"                                        <div id=\"regentrymsg2\"></div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Scope</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryscope2\"  />\n" +
"\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Name</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryname2\" placeholder=\"Example:com.express_scripts.services\" />\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Value</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryvalue2\" placeholder=\"Example:ref_product_app_dev1\"/>\n" +
"                                        </div>\n" +
"\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" id=\"regentryconfid2\" value=\"\" />Confidential\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                            <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" id=\"useinheritance\" value=\"\"/>Use Inheritance\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                            <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" id=\"casesensitive\" value=\"\"/>Case Sensitive\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"\n" +
"\n" +
"                                        <button type=\"submit\" id=\"regentrysearchbtn\" class=\"btn btn-default\">Search </button>\n" +
"                                        <button type=\"reset\" class=\"btn btn-primary\">Reset </button>\n" +
"                                   <div id=\"dispdiv\" class=\"col-md-offset-1 col-md-10\"></div>\n" +
"                                    </form>\n" +
"                                    \n" +
"                                    \n" +
"                                </div>\n" +
"\n" +
"                                \n" +
"                                <div id=\"dvUpdate\"  class=\"panel-body\">\n" +
"\n" +
"                     <form role=\"form\" method=\"post\" action=\"UpdaServlet\">\n" +
"                        <div id=\"regentrymsg3\">ID not Exists</div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>ID</label>\n" +
"                                            <input class=\"form-control\" name=\"id\" id=\"regentryidx\"/>\n" +
"                                           \n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Scope</label>\n" +
"                                            <input class=\"form-control\" name=\"scope\" id=\"regentryscopex\"/>\n" +
"                                           \n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Name</label>\n" +
"                                            <input class=\"form-control\" name=\"name\" id=\"regentrynamex\" placeholder=\"Example:com.express_scripts.services\" />\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Value</label>\n" +
"                                            <input class=\"form-control\" name=\"value\" id=\"regentryvaluex\"placeholder=\"Example:ref_product_app_dev1\"/>\n" +
"                                        </div>\n" +
"        \n" +
"                                        <div class=\"form-group\">\n" +
"                                                <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" name=\"checkbox\" id=\"regentryconfidx\" value=\"\" />Confidential\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"\n" +
"                               \n" +
"                                        <button type=\"submit\" id=\"regentryupdatebtn\">Update</button>\n" +
"                                        <button  class=\"btn btn-default\">Cancel</button>\n" +
"\n" +
"                                    </form>                         \n" +
"                                </div>\n" +
"\n" +
"                                <div id=\"dvDelete\"  class=\"panel-body\">\n" +
"                                  \n" +
"                                    <form role=\"form\" method=\"post\" action=\"DeleteServlet\">\n" +
"                                        <div id=\"regentrymsg4\"></div>\n" +
"                                        <div class=\"form-group\" >\n" +
"                                            <label>ID</label>\n" +
"                                            <input  type=\"text\" id=\"reid\" name=\"id\" class=\"form-control\"  />\n" +
"\n" +
"                                        </div>\n" +
"\n" +
"\n" +
"                                        <br/>\n" +
"                                        <button type=\"submit\" id=\"regentrydelbtn0\">Delete</button>\n" +
"                                        <button  class=\"btn btn-default\">Cancel</button>\n" +
"                                    </form>                            \n" +
"                                </div>\n" +
"\n" +
"                            </div>\n" +
"                            <!-- End Form Elements -->\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\"></div>\n" +
"                    </div>\n" +
"                    <!-- /. ROW  -->\n" +
"\n" +
"                    <!-- /. ROW  -->\n" +
"                </div>\n" +
"                <!-- /. PAGE INNER  -->\n" +
"            </div>\n" +
"            <!-- /. PAGE WRAPPER  -->\n" +
"        </div>\n" +
"        <!-- /. WRAPPER  -->\n" +
"        <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->\n" +
"        <!-- JQUERY SCRIPTS -->\n" +
"        <!-- script src=\"js/jquery-1.10.2.js\"></script> -->\n" +
"        <!-- BOOTSTRAP SCRIPTS -->\n" +
"        <script src=\"js/bootstrap.min.js\"></script>\n" +
"        <!-- METISMENU SCRIPTS -->\n" +
"        <!-- <script src=\"js/jquery.metisMenu.js\"></script> -->\n" +
"        <!-- CUSTOM SCRIPTS -->\n" +
"        <script src=\"js/custom.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"js/registry.js\"></script>\n" +
"        <script>\n" +
"\n" +
"            $(\"#dvSearch\").hide();\n" +
"            $(\"#htagSearch\").hide();\n" +
"            $(\"#dvAdd\").hide();\n" +
"            $(\"#htagAdd\").hide();\n" +
"            $(\"#htagUpdate\").show();\n" +
"            $(\"#dvUpdate\").show();\n" +
"            $(\"#htagDelete\").hide();\n" +
"            $(\"#dvDelete\").hide();\n" +
"\n" +
"            $(\"#anchAddEntry\").click(function () {\n" +
"                $(\"#dvAdd\").show();\n" +
"                $(\"#htagAdd\").show();\n" +
"                $(\"#dvSearch\").hide();\n" +
"                $(\"#htagSearch\").hide();\n" +
"                $(\"#htagUpdate\").hide();\n" +
"            $(\"#dvUpdate\").hide();\n" +
"                $(\"#htagDelete\").hide();\n" +
"                $(\"#dvDelete\").hide();\n" +
"            });\n" +
"\n" +
"            $(\"#anchSearchEntry\").click(function () {\n" +
"                $(\"#dvAdd\").hide();\n" +
"                $(\"#htagAdd\").hide();\n" +
"                $(\"#dvSearch\").show();\n" +
"                $(\"#htagSearch\").show();\n" +
"                $(\"#htagUpdate\").hide();\n" +
"            $(\"#dvUpdate\").hide();\n" +
"                $(\"#htagDelete\").hide();\n" +
"                $(\"#dvDelete\").hide();\n" +
"            });\n" +
"            \n" +
"                        $(\"#anchDeleteEntry\").click(function () {\n" +
"                $(\"#dvAdd\").hide();\n" +
"                $(\"#htagAdd\").hide();\n" +
"                $(\"#dvSearch\").hide();\n" +
"                $(\"#htagSearch\").hide();\n" +
"                $(\"#htagDelete\").show();\n" +
"                $(\"#dvDelete\").show();\n" +
"                $(\"#htagUpdate\").hide();\n" +
"            $(\"#dvUpdate\").hide();\n" +
"            });\n" +
"            \n" +
"            \n" +
"            $(\"#anchUpdateEntry\").click(function () {\n" +
"                $(\"#dvAdd\").hide();\n" +
"                $(\"#htagAdd\").hide();\n" +
"                $(\"#dvSearch\").hide();\n" +
"                $(\"#htagSearch\").hide();\n" +
"                $(\"#htagDelete\").hide();\n" +
"                $(\"#dvDelete\").hide();\n" +
"                $(\"#htagUpdate\").show();\n" +
"            $(\"#dvUpdate\").show();\n" +
"            });\n" +
"\n" +
"\n" +
"\n" +
"        </script>\n" +
"\n" +
"\n" +
"    </body>\n" +
"</html>\n" +
"");
            } 
            else {
                out.println("\n" +
"<!DOCTYPE html>\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\" />\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
"        <title>Free Bootstrap Admin Template : Binary Admin</title>\n" +
"        <script type=\"text/javascript\" src=\"js/jquery-1.12.4.min.js\"></script>\n" +
"<script type=\"text/javascript\" src=\"js/bootstrap.min.js\"></script>\n" +
"<script type=\"text/javascript\" src=\"js/registry.js\"></script>\n" +
"<!--  --link href=\"css/registry.css\" rel=\"stylesheet\"--!>\n" +
"<!-- Bootstrap -->\n" +
"<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">\n" +
"<link href=\"css/bootstrap-theme.css\" rel=\"stylesheet\" media=\"screen\">\n" +
"<link href=\"css/bootstrap-theme.min.css\" rel=\"stylesheet\" media=\"screen\">\n" +
"        <!-- BOOTSTRAP STYLES-->\n" +
"        <link href=\"css/bootstrap.css\" rel=\"stylesheet\" />\n" +
"        <!-- FONTAWESOME STYLES-->\n" +
"        <link href=\"css/font-awesome.css\" rel=\"stylesheet\" />\n" +
"        <!-- CUSTOM STYLES-->\n" +
"        <link href=\"css/custom.css\" rel=\"stylesheet\" />\n" +
"        <!-- GOOGLE FONTS-->\n" +
"        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />\n" +
"    </head>\n" +
"    <body>\n" +
"        <div id=\"wrapper\">\n" +
"            <nav class=\"navbar navbar-default navbar-cls-top \" role=\"navigation\" style=\"margin-bottom: 0\">\n" +
"                <div class=\"navbar-header\">\n" +
"                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".sidebar-collapse\">\n" +
"                        <span class=\"sr-only\">Toggle navigation</span>\n" +
"                        <span class=\"icon-bar\"></span>\n" +
"                        <span class=\"icon-bar\"></span>\n" +
"                        <span class=\"icon-bar\"></span>\n" +
"                    </button>\n" +
"\n" +
"                </div>\n" +
"                <div style=\"color: white;\n" +
"                     padding: 15px 50px 5px 50px;\n" +
"                     float: right;\n" +
"                     font-size: 16px;\"> </div>\n" +
"            </nav>   \n" +
"            <!-- /. NAV TOP  -->\n" +
"            <nav class=\"navbar-default navbar-side\" role=\"navigation\">\n" +
"                <div class=\"sidebar-collapse\">\n" +
"                    <ul class=\"nav\" id=\"main-menu\">\n" +
"                        <li class=\"text-center\">\n" +
"                            <img src=\"img/find_user.png\" class=\"user-image img-responsive\"/>\n" +
"                        </li>\n" +
"                        \n" +
"                        <li>\n" +
"                            <a id=\"anchAddEntry\"  href=\"#\"  ><i class=\"fa fa-plus-square fa-3x\"></i> Add Entry</a>\n" +
"                        </li>\n" +
"\n" +
"                        <li>\n" +
"                            <a id=\"anchSearchEntry\"  href=\"#\"><i class=\"fa fa-search fa-3x\"></i> Search Entry</a>\n" +
"                        </li>\n" +
"                        \n" +
"                        <li>\n" +
"                            <a id=\"anchUpdateEntry\"  href=\"#\"><i class=\"fa fa-pencil-square-o fa-3x\"></i> Update Entry</a>\n" +
"                        </li>\n" +
"                        <li  >\n" +
"                            <a id=\"anchDeleteEntry\"  href=\"#\"><i class=\"fa fa-trash-o fa-3x\"></i> Delete Entry</a>\n" +
"                        </li>	\n" +
"\n" +
"                    </ul>\n" +
"\n" +
"                </div>\n" +
"\n" +
"            </nav>  \n" +
"            <!-- /. NAV SIDE  -->\n" +
"            <div id=\"page-wrapper\" >\n" +
"                <div id=\"page-inner\">\n" +
"                    <div class=\"row\">\n" +
"\n" +
"                    </div>\n" +
"\n" +
"                    <hr />\n" +
"                    <div class=\"row\">\n" +
"                        <div class=\"col-md-3\"></div>\n" +
"                        <div class=\"col-md-6\">\n" +
"                            <!-- Form Elements -->\n" +
"                            <div class=\"panel panel-default\">\n" +
"                                <div class=\"panel-heading\">\n" +
"                                    <h3 id=\"htagAdd\">Add Registry Entry </h3>\n" +
"                                    <h3 id=\"htagSearch\">Search Registry Entry </h3>\n" +
"                                    <h3 id =\"htagUpdate\">Update Registry</h3>\n" +
"                                    <h3 id =\"htagDelete\">Delete Registry</h3>\n" +
"                                </div>\n" +
"                                <div id=\"dvAdd\"  class=\"panel-body\">\n" +
"                                    \n" +
"                                    <form role=\"form\">\n" +
"                                        <div id=\"regentrymsg1\"></div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Scope</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryscope\" type=\"text\"/>\n" +
"\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Name</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryname\" type=\"text\" placeholder=\"Example:com.express_scripts.services\" />\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Value</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryvalue\" type=\"text\" placeholder=\"Example:ref_product_app_dev1\"/>\n" +
"                                        </div>\n" +
"\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" value=\"\" id=\"regentryconfid\">Confidential\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"\n" +
"                                        <br/>\n" +
"                                        <button class=\"btn btn-default\" type=\"button\" id=\"regentryaddbtn\">Add</button>\n" +
"                                        <button  class=\"btn btn-default\">Cancel</button>\n" +
"                                        \n" +
"\n" +
"                                    </form>                            \n" +
"                                </div>\n" +
"                                <div  id=\"dvSearch\" class=\"panel-body\">\n" +
"                                    \n" +
"                                    <form role=\"form\">\n" +
"                                        <div id=\"regentrymsg2\"></div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Scope</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryscope2\"  />\n" +
"\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Name</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryname2\" placeholder=\"Example:com.express_scripts.services\" />\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Value</label>\n" +
"                                            <input class=\"form-control\" id=\"regentryvalue2\" placeholder=\"Example:ref_product_app_dev1\"/>\n" +
"                                        </div>\n" +
"\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" id=\"regentryconfid2\" value=\"\" />Confidential\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                            <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" id=\"useinheritance\" value=\"\"/>Use Inheritance\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                            <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" id=\"casesensitive\" value=\"\"/>Case Sensitive\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"\n" +
"\n" +
"                                        <button type=\"submit\" id=\"regentrysearchbtn\" class=\"btn btn-default\">Search </button>\n" +
"                                        <button type=\"reset\" class=\"btn btn-primary\">Reset </button>\n" +
"                                   <div id=\"dispdiv\" class=\"col-md-offset-1 col-md-10\"></div>\n" +
"                                    </form>\n" +
"                                    \n" +
"                                    \n" +
"                                </div>\n" +
"\n" +
"                                \n" +
"                                <div id=\"dvUpdate\"  class=\"panel-body\">\n" +
"\n" +
"                     <form role=\"form\" method=\"post\" action=\"UpdaServlet\">\n" +
"                        <div id=\"regentrymsg3\">Entry Updated Successfully</div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>ID</label>\n" +
"                                            <input class=\"form-control\" name=\"id\" id=\"regentryidx\"/>\n" +
"                                           \n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Scope</label>\n" +
"                                            <input class=\"form-control\" name=\"scope\" id=\"regentryscopex\"/>\n" +
"                                           \n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Name</label>\n" +
"                                            <input class=\"form-control\" name=\"name\" id=\"regentrynamex\" placeholder=\"Example:com.express_scripts.services\" />\n" +
"                                        </div>\n" +
"                                        <div class=\"form-group\">\n" +
"                                            <label>Value</label>\n" +
"                                            <input class=\"form-control\" name=\"value\" id=\"regentryvaluex\"placeholder=\"Example:ref_product_app_dev1\"/>\n" +
"                                        </div>\n" +
"        \n" +
"                                        <div class=\"form-group\">\n" +
"                                                <div class=\"checkbox\">\n" +
"                                                <label>\n" +
"                                                    <input type=\"checkbox\" name=\"checkbox\" id=\"regentryconfidx\" value=\"\" />Confidential\n" +
"                                                </label>\n" +
"                                            </div>\n" +
"                                        </div>\n" +
"\n" +
"                               \n" +
"                                        <button type=\"submit\" id=\"regentryupdatebtn\">Update</button>\n" +
"                                        <button  class=\"btn btn-default\">Cancel</button>\n" +
"\n" +
"                                    </form>                         \n" +
"                                </div>\n" +
"\n" +
"                                <div id=\"dvDelete\"  class=\"panel-body\">\n" +
"                                  \n" +
"                                    <form role=\"form\" method=\"post\" action=\"DeleteServlet\">\n" +
"                                        <div id=\"regentrymsg4\"></div>\n" +
"                                        <div class=\"form-group\" >\n" +
"                                            <label>ID</label>\n" +
"                                            <input  type=\"text\" id=\"reid\" name=\"id\" class=\"form-control\"  />\n" +
"\n" +
"                                        </div>\n" +
"\n" +
"\n" +
"                                        <br/>\n" +
"                                        <button type=\"submit\" id=\"regentrydelbtn0\">Delete</button>\n" +
"                                        <button  class=\"btn btn-default\">Cancel</button>\n" +
"                                    </form>                            \n" +
"                                </div>\n" +
"\n" +
"                            </div>\n" +
"                            <!-- End Form Elements -->\n" +
"                        </div>\n" +
"                        <div class=\"col-md-3\"></div>\n" +
"                    </div>\n" +
"                    <!-- /. ROW  -->\n" +
"\n" +
"                    <!-- /. ROW  -->\n" +
"                </div>\n" +
"                <!-- /. PAGE INNER  -->\n" +
"            </div>\n" +
"            <!-- /. PAGE WRAPPER  -->\n" +
"        </div>\n" +
"        <!-- /. WRAPPER  -->\n" +
"        <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->\n" +
"        <!-- JQUERY SCRIPTS -->\n" +
"        <!-- script src=\"js/jquery-1.10.2.js\"></script> -->\n" +
"        <!-- BOOTSTRAP SCRIPTS -->\n" +
"        <script src=\"js/bootstrap.min.js\"></script>\n" +
"        <!-- METISMENU SCRIPTS -->\n" +
"        <!-- <script src=\"js/jquery.metisMenu.js\"></script> -->\n" +
"        <!-- CUSTOM SCRIPTS -->\n" +
"        <script src=\"js/custom.js\"></script>\n" +
"        <script type=\"text/javascript\" src=\"js/registry.js\"></script>\n" +
"        <script>\n" +
"\n" +
"            $(\"#dvSearch\").hide();\n" +
"            $(\"#htagSearch\").hide();\n" +
"            $(\"#dvAdd\").hide();\n" +
"            $(\"#htagAdd\").hide();\n" +
"            $(\"#htagUpdate\").show();\n" +
"            $(\"#dvUpdate\").show();\n" +
"            $(\"#htagDelete\").hide();\n" +
"            $(\"#dvDelete\").hide();\n" +
"\n" +
"            $(\"#anchAddEntry\").click(function () {\n" +
"                $(\"#dvAdd\").show();\n" +
"                $(\"#htagAdd\").show();\n" +
"                $(\"#dvSearch\").hide();\n" +
"                $(\"#htagSearch\").hide();\n" +
"                $(\"#htagUpdate\").hide();\n" +
"            $(\"#dvUpdate\").hide();\n" +
"                $(\"#htagDelete\").hide();\n" +
"                $(\"#dvDelete\").hide();\n" +
"            });\n" +
"\n" +
"            $(\"#anchSearchEntry\").click(function () {\n" +
"                $(\"#dvAdd\").hide();\n" +
"                $(\"#htagAdd\").hide();\n" +
"                $(\"#dvSearch\").show();\n" +
"                $(\"#htagSearch\").show();\n" +
"                $(\"#htagUpdate\").hide();\n" +
"            $(\"#dvUpdate\").hide();\n" +
"                $(\"#htagDelete\").hide();\n" +
"                $(\"#dvDelete\").hide();\n" +
"            });\n" +
"            \n" +
"                        $(\"#anchDeleteEntry\").click(function () {\n" +
"                $(\"#dvAdd\").hide();\n" +
"                $(\"#htagAdd\").hide();\n" +
"                $(\"#dvSearch\").hide();\n" +
"                $(\"#htagSearch\").hide();\n" +
"                $(\"#htagDelete\").show();\n" +
"                $(\"#dvDelete\").show();\n" +
"                $(\"#htagUpdate\").hide();\n" +
"            $(\"#dvUpdate\").hide();\n" +
"            });\n" +
"            \n" +
"            \n" +
"            $(\"#anchUpdateEntry\").click(function () {\n" +
"                $(\"#dvAdd\").hide();\n" +
"                $(\"#htagAdd\").hide();\n" +
"                $(\"#dvSearch\").hide();\n" +
"                $(\"#htagSearch\").hide();\n" +
"                $(\"#htagDelete\").hide();\n" +
"                $(\"#dvDelete\").hide();\n" +
"                $(\"#htagUpdate\").show();\n" +
"            $(\"#dvUpdate\").show();\n" +
"            });\n" +
"\n" +
"\n" +
"\n" +
"        </script>\n" +
"\n" +
"\n" +
"    </body>\n" +
"</html>\n" +
"");
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}