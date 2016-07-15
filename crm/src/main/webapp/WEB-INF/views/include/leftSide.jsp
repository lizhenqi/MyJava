<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info" style="margin-top: 20px">
                <p>别名：<shiro:principal property="username"/></p>
                <%--<!-- Status -->--%>
                <%--<a href="#"><i class="fa fa-circle text-success"></i>在线</a>--%>
            </div>
        </div>


        <!-- Sidebar Menu -->

        <ul class="sidebar-menu">

            <%--多个权限之间用逗号分开--%>
            <shiro:hasAnyRoles name="管理员,经理,员工">
                <li class="${param.menu=="home"?"active":''}"><a href="/home"><i class=" icon-eye-open"></i> <span>首页</span></a></li>
                <li class="${param.menu=="notice"?"active":''}"><a href="/notice"><i class=" icon-volume-up"></i> <span>公告</span></a></li>
                <li><a href="#"><i class=" icon-bookmark"></i> <span>项目管理</span></a></li>
                <li class="${param.menu=="customer"?"active":''}"><a href="/customer"><i class=" icon-group"></i> <span href="/customer">客户管理</span></a></li>
                <li><a href="#"><i class="icon-align-left"></i> <span>统计</span></a></li>
                <li><a href="#"><i class=" icon-bell"></i> <span>代办事项</span></a></li>
                <li class="${param.menu=="doc"?"active":''}"><a href="/doc"><i class=" icon-folder-open-alt"></i> <span>文档管理</span></a></li>
            </shiro:hasAnyRoles>
            <%--设置为只有管理员才能看到以下部分--%>
            <%--下面的name根据ShiroRoleam里面的PrincipalCollection，返回的用户角色判定如果是name里面的角色则能查看--%>
            <shiro:hasRole name="管理员">
                <li class="treeview">
                    <a href="#"><i class="icon-cogs"></i> <span>系统管理</span> <i
                            class="icon-caret-right pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li class="${param.menu=="user"?"active":''}"><a href="/admin/user"><i class=" icon-user-md" style="margin-left: 30%">员工管理</i></a></li>
                        <li><a href="#"><i class=" icon-wrench" style="margin-left: 30%">系统设置</i></a></li>
                    </ul>
                </li>
            </shiro:hasRole>

        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
