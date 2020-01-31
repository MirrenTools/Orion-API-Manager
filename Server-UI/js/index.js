/**
 * Created by Mirren on 2018/10/19.
 */

/**
 * 加载项目列表
 * @param complete 加载完成事件
 */
function loadProjectList(complete) {
    doAJAX(METHOD_GET, SERVER_HOST + '/project', null, function (result) {
        if (result.code == 200) {
            var data = result.data;
            for (var i = 0; i < data.length; i++) {
                var txt = "<li id='" + data[i].key + "' onclick=\"requestProject('" + data[i].key + "')\" ><a class='cursor-hand'>" + data[i].name + " <span class='badge'>" + data[i].version + "</span></a></li>";
                $("#project_list").append($(txt));
            }
            complete();//执行加载完成事件
        } else {
            console.log("msg:" + result.msg + " ,data:");
            console.log(result.data);
            confirm("获取项目列表失败,");
        }
    }, function (e) {
        console.log("获取项目列表失败...");
        console.log(e);
        var state = e.readyState;
        if (state == 0) {
            alert('获取数据需要先启动服务器,请在当前目录双击start.bat');
        } else {
            confirm('获取数据失败了');
        }
    });
}

/**
 * 从服务器获取指定项目的数据
 * @param id
 */
function getProject(id, res) {
    doAJAX(METHOD_GET, SERVER_HOST + '/project/' + id, null, function (result) {
        if (result.code == 200) {
            return res(result.data);
        } else {
            console.log("msg:" + result.msg + " ,data:");
            console.log(result.data);
            confirm("获取项目失败,");
        }
    }, function (e) {
        console.log("获取数据失败...");
        console.log(e);
        var state = e.readyState;
        if (state == 0) {
            alert('获取数据需要先启动服务器,请在当前目录双击start.bat');
        } else {
            confirm('获取数据失败了');
        }
    });
}

/**
 * 请求项目
 * @param id
 */
function requestProject(id) {
    var href = location.href;
    if (href.indexOf('?') != -1) {
        href = href.substring(0, href.indexOf('?'));
    }
    location.href = href + "?pid=" + id;
}

/**
 * 显示项目
 * @param id
 */
function loadProject(id) {
    $("#project_info_body").html('<h1 class="page-header">信息加载中...</h1>');
    getProject(id, function (data) {
        try {
            loadProjectInfo(data);//加载项目信息
            loadApiGroup(id);//加载分组信息
        } catch (e) {
            $("#project_info_body").html('<h1 class="page-header">点击项目列表加载项目信息</h1>');
            console.log(e)
        }
    });
}

/**
 * 加载接口分组
 * @param projectId
 */
function loadApiGroup(projectId) {
    if (projectId == null || projectId == '') {
        alert('项目的id不能为空!');
    }
    doAJAX(METHOD_GET, SERVER_HOST + '/project/apiGroup/' + projectId, null, function (result) {
        if (result.code == 200) {
            console.log('加载项目接口分组成功!');
            loadApiGroupAndApiOpblack(result.data);
        } else {
            console.log("msg:" + result.msg + " ,data:");
            console.log(result.data);
            confirm("加载项目接口分组失败");
        }
    }, function (e) {
        console.log("加载项目接口分组失败...");
        console.log(e);
        var state = e.readyState;
        if (state == 0) {
            alert('加载项目接口分组需要先启动服务器,请在当前目录双击start.bat');
        } else {
            confirm('加载项目接口分组失败了');
        }
    });
}


/**
 * 返回项目所需要的swagger信息,创建时用,如果修改需要加入额外的属性
 */
function getProjectInfo() {
    var name = $('#input_name').val();
    var version = $('#input_version').val();
    var host = $('#input_host').val();
    if (name == '' || version == '' || host == '') {
        alert('项目名称,版本号,主机 为必填项');
        return;
    }
    var project = {};
    project.name = name;
    project.versions = version;
    if ($('#input_description').val().trim() != '') {
        project.description = $('#input_description').val().trim();
    }
    project.host = host;
    if ($("#input_basePath").val().trim() != '') {
        project.basePath = $("#input_basePath").val().trim();
    }
    //服务协议
    var schemes = [];
    $('input:checkbox[name=input_schemes]:checked').each(function (k) {
        schemes.push($(this).val());
    });
    if (schemes.length > 0) {
        project.schemes = JSON.stringify(schemes);
    }

    //拓展文档
    var externalDocs = {};
    var externalDocs_description = $("#input_externalDocs_description").val();
    if (externalDocs_description != '') {
        externalDocs.description = externalDocs_description;
    }
    var externalDocs_url = $("#input_externalDocs_url").val();
    if (externalDocs_url != '') {
        externalDocs.url = externalDocs_url;
    }
    if (!jQuery.isEmptyObject(externalDocs)) {
        project.externalDocs = JSON.stringify(externalDocs);
    }

    //联系方式
    if ($('#input_contact_name').val().trim() != '') {
        project.contactName = $('#input_contact_name').val().trim();
    }
    if ($('#input_contact_info').val().trim() != '') {
        project.contactInfo = $('#input_contact_info').val().trim();
    }
    return project;
}

/**创建项目*/
function createProject() {
    var project = getProjectInfo();
    if (IS_DEBUG_ENABLED) {
        console.log(project);
    }
    //调用Index的doAJAX
    doAJAX(METHOD_POST, SERVER_HOST + '/project', project, function (result) {
        if (result.code == 200) {
            if (confirm('创建成功!是否继续创建?')) {
                window.location.reload();
            } else {
                self.location = "index.html";
            }
        } else {
            alert("创建应用失败");
            console.log("msg:" + result.msg + " ,data:");
            console.log(result.data);
        }
    }, function (e) {
        console.log("创建数据失败...");
        console.log(e);
        var state = e.readyState;
        if (state == 0) {
            alert('获取数据需要先启动服务器,请在当前目录双击start.bat');
        } else {
            alert('获取数据失败了');
        }
    });
}

/**加载修改项目所需要用的相关属性*/
function loadUpdateProject(id) {
    getProject(id, function (data) {
        if (IS_DEBUG_ENABLED) {
            console.log(data);
        }
        $("#project_id").val(data.key);
        $("#input_name").val(data.name);
        $("#input_version").val(data.versions);
        $("#input_host").val(data.host);
        if (data.description != null) {
            $("#input_description").val(data.description);
        }
        if (data.basePath != null) {
            $("#input_basePath").val(data.basePath);
        }
        if (data.schemes != null) {
            var schemes = JSON.parse(data.schemes);
            for (var i = 0; i < schemes.length; i++) {
                $("#input_schemes_" + schemes[i]).attr("checked", "checked");
            }
        }
        if (data.externalDocs != null) {
            var externalDocs = JSON.parse(data.externalDocs);
            $("#input_externalDocs_description").val(externalDocs.description);
            $("#input_externalDocs_url").val(externalDocs.url);
        }
        if (data.contactName != null) {
            $("#input_contact_name").val(data.contactName);
        }
        if (data.contactInfo != null) {
            $("#input_contact_info").val(data.contactInfo);
        }
        //=这里可以加载更多属性
    })
}

/**修改项目*/
function updateProject() {
    var project = getProjectInfo();
    if (project.schemes == null) {
        project.schemes = "[]";
    }
    if (project.externalDocs == null) {
        project.externalDocs = "{}";
    }
    if (project.contactName == null) {
        project.contactName = '';
    }
    if (project.contactInfo == null) {
        project.contactInfo = '';
    }
    if (IS_DEBUG_ENABLED) {
        console.log(project);
    }

    project.key = $("#project_id").val();
    //如果还有更多属性,可以在这里获取相应的属性,后添加到project中
    //调用Index的doAJAX
    doAJAX(METHOD_PUT, SERVER_HOST + '/project', project, function (result) {
        if (result.code == 200) {
            alert("修改成功");
            self.location = "./index.html?pid=" + project.key;
        } else {
            console.log("msg:" + result.msg + " ,data:");
            console.log(result.data);
            confirm("修改项目失败");
        }
    }, function (e) {
        console.log("修改项目失败...");
        console.log(e);
        var state = e.readyState;
        if (state == 0) {
            alert('修改项目需要先启动服务器,请在当前目录双击start.bat');
        } else {
            confirm('修改项目失败了');
        }
    });
}

/**
 * 项目复制一份副本
 * @param id 项目的id
 */
function copyProject(id) {
    if (confirm('确定复制该副本吗?')) {
        doAJAX(METHOD_POST, SERVER_HOST + '/project/copy/' + id, null, function (result) {
            if (result.code == 200) {
                console.log("复制成功");
                alert('复制成功!');
                self.location = "./index.html?pid=" + id;
            } else {
                console.log("msg:" + result.msg + " ,data:");
                console.log(result.data);
                confirm("复制项目失败");
            }
        }, function (e) {
            console.log("复制项目失败...");
            console.log(e);
            var state = e.readyState;
            if (state == 0) {
                alert('复制项目需要先启动服务器,请在当前目录双击start.bat');
            } else {
                confirm('复制项目失败了');
            }
        });
    }
}


/**
 * 删除项目
 * @param id
 */
function deletProject(id) {
    if (confirm('确定删除该项目吗?')) {
        doAJAX(METHOD_DELETE, SERVER_HOST + '/project/' + id, null, function (result) {
            if (result.code == 200) {
                console.log("删除成功");
                location.reload();
            } else {
                confirm("删除项目失败");
                console.log("msg:" + result.msg + " ,data:");
                console.log(result.data);
                location.reload();
            }
        }, function (e) {
            console.log("删除项目失败...");
            console.log(e);
            var state = e.readyState;
            if (state == 0) {
                alert('删除项目需要先启动服务器,请在当前目录双击start.bat');
            } else {
                confirm('删除项目失败了');
            }
        });
    }
}

/**
 * 获取分组消息,给方法只获取新增时所需的数据,id等其他数据需要在调用该方法后获取
 * @returns {{}}
 */
function getApiGroupInfo() {
    var name = $("#api_group_name").val();
    var summary = $("#api_group_summary").val();
    //分组消息的信息
    var group = {};
    group.name = name;
    group.summary = summary;
    var description = $("#api_group_description").val();
    if (description != '') {
        group.description = description;
    }
    //拓展文档
    var docs = {};
    var docs_description = $("#api_group_externalDocs_description").val();
    if (docs_description != '') {
        docs.description = docs_description;
    }
    var docs_url = $("#api_group_externalDocs_url").val();
    if (docs_url != '') {
        docs.url = docs_url;
    }
    if (!jQuery.isEmptyObject(docs)) {
        group.externalDocs = JSON.stringify(docs);
    }
    return group
}

/**
 * 新建接口分组
 */
function createApiGroup() {
    var projectId = $("#hide_project_id").val();
    var group = getApiGroupInfo();
    group.projectId = projectId;
    if (group.name == '' || group.summary == '') {
        alert('分组的名称与简介为必填项!');
        return;
    }
    doAJAX(METHOD_POST, SERVER_HOST + '/apiGroup', group, function (result) {
        if (result.code == 200) {
            console.log('新增分组成功');
            location.reload();
        } else {
            console.log("msg:" + result.msg + " ,data:");
            console.log(result.data);
            confirm("新增分组失败");
            location.reload();
        }
    }, function (e) {
        console.log("新增分组失败...");
        console.log(e);
        var state = e.readyState;
        if (state == 0) {
            alert('新增分组需要先启动服务器,请在当前目录双击start.bat');
        }
    });
}

/**
 * 加载分组要修改的信息
 * @param id
 */
function loadUpdateApiGroup(id) {
    doAJAX(METHOD_GET, SERVER_HOST + '/apiGroup/' + id, null, function (result) {
        if (result.code == 200) {
            console.log('加载接口分组修改数据成功!');
            if (IS_DEBUG_ENABLED) {
                console.log(result.data)
            }
            updateApiGroupShow(result.data);
        } else {
            console.log("msg:" + result.msg + " ,data:");
            console.log(result.data);
            confirm("加载接口分组修改数据失败");
        }
    }, function (e) {
        console.log("加载接口分组修改数据失败...");
        console.log(e);
        var state = e.readyState;
        if (state == 0) {
            alert('加载项目接口分组修改数据需要先启动服务器,请在当前目录双击start.bat');
        }
    });
}

/**
 * 修改接口分组
 */
function updateApiGroup() {
    var group = getApiGroupInfo();
    group.groupId = $("#hide_api_group_id").val();
    if (group.groupId == '' || group.name == '' || group.summary == '') {
        alert('分组的名称与简介为必填项!');
        return;
    }
    if (group.description == null) {
        group.description = "";
    }
    if (group.externalDocs == null || group.externalDocs == '') {
        group.externalDocs = "{}";
    }
    if (IS_DEBUG_ENABLED) {
        console.log(group);
    }
    doAJAX(METHOD_PUT, SERVER_HOST + '/apiGroup', group, function (result) {
        if (result.code == 200) {
            console.log('修改接口分组修改数据成功!');
            refreshApiGroupInfo(group);
        } else {
            console.log("msg:" + result.msg + " ,data:");
            console.log(result.data);
            confirm("修改接口分组修改数据失败");
        }
    }, function (e) {
        console.log("修改接口分组修改数据失败...");
        console.log(e);
        var state = e.readyState;
        if (state == 0) {
            alert('修改接口分组修改数据需要先启动服务器,请在当前目录双击start.bat');
        }
    });
}

/**
 * 删除接口分组
 * @param id
 */
function deleteApiGroup(id) {
    if (confirm('确定删除该接口分组?')) {
        doAJAX(METHOD_DELETE, SERVER_HOST + '/apiGroup/' + id, null, function (result) {
            if (result.code == 200) {
                console.log('删除接口分组成功!');
                location.reload();
            } else {
                console.log("msg:" + result.msg + " ,data:");
                console.log(result.data);
                confirm("删除接口分组失败");
                location.reload();
            }
        }, function (e) {
            console.log("删除接口分组失败...");
            console.log(e);
            var state = e.readyState;
            if (state == 0) {
                alert('删除接口分组需要先启动服务器,请在当前目录双击start.bat');
            }
        });
    }
}
















