(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7fb4d854"],{"0db5":function(t,e,r){"use strict";r.d(e,"n",(function(){return a})),r.d(e,"r",(function(){return n})),r.d(e,"y",(function(){return i})),r.d(e,"e",(function(){return s})),r.d(e,"B",(function(){return l})),r.d(e,"u",(function(){return c})),r.d(e,"t",(function(){return u})),r.d(e,"i",(function(){return p})),r.d(e,"o",(function(){return d})),r.d(e,"z",(function(){return g})),r.d(e,"C",(function(){return m})),r.d(e,"j",(function(){return v})),r.d(e,"m",(function(){return f})),r.d(e,"x",(function(){return h})),r.d(e,"v",(function(){return _})),r.d(e,"b",(function(){return D})),r.d(e,"a",(function(){return x})),r.d(e,"f",(function(){return b})),r.d(e,"l",(function(){return $})),r.d(e,"p",(function(){return y})),r.d(e,"w",(function(){return w})),r.d(e,"A",(function(){return S})),r.d(e,"d",(function(){return C})),r.d(e,"c",(function(){return I})),r.d(e,"g",(function(){return j})),r.d(e,"k",(function(){return G})),r.d(e,"q",(function(){return k})),r.d(e,"s",(function(){return M})),r.d(e,"h",(function(){return T}));var o=r("a5f4");function a(t,e){o["a"].get("/private/project").then(t).catch(e)}function n(t,e,r){o["a"].get("/private/project/"+t).then(e).catch(r)}function i(t,e,r){o["a"].post("/private/server/project/",t).then(e).catch(r)}function s(t,e,r){o["a"].post("/private/server/project/copy/"+t).then(e).catch(r)}function l(t,e,r){o["a"].put("/private/server/project/",t).then(e).catch(r)}function c(t,e,r){o["a"].put("/private/server/project/moveUp/"+t).then(e).catch(r)}function u(t,e,r){o["a"].put("/private/server/project/moveDown/"+t).then(e).catch(r)}function p(t,e,r){o["a"].delete("/private/server/project/"+t).then(e).catch(r)}function d(t,e,r){o["a"].get("/private/projectShare/"+t).then(e).catch(r)}function g(t,e,r){o["a"].post("/private/server/projectShare",t).then(e).catch(r)}function m(t,e,r){o["a"].put("/private/server/projectShare",t).then(e).catch(r)}function v(t,e,r){o["a"].delete("/private/server/projectShare/"+t).then(e).catch(r)}function f(t,e,r){o["a"].get("/private/server/project/apiGroup/"+t).then(e).catch(r)}function h(t,e,r){o["a"].post("/private/server/apiGroup",t).then(e).catch(r)}function _(t,e,r){o["a"].put("/private/server/apiGroup",t).then(e).catch(r)}function D(t,e,r){o["a"].put("/private/server/apiGroup/moveUp/"+t).then(e).catch(r)}function x(t,e,r){o["a"].put("/private/server/apiGroup/moveDown/"+t).then(e).catch(r)}function b(t,e,r){o["a"].delete("/private/server/apiGroup/"+t).then(e).catch(r)}function $(t,e,r){o["a"].get("/private/server/apis/"+t).then(e).catch(r)}function y(t,e,r){o["a"].get("/private/server/api/"+t).then(e).catch(r)}function w(t,e,r){o["a"].post("/private/server/api",t).then(e).catch(r)}function S(t,e,r){o["a"].put("/private/server/api",t).then(e).catch(r)}function C(t,e,r){o["a"].put("/private/server/api/moveUp/"+t).then(e).catch(r)}function I(t,e,r){o["a"].put("/private/server/api/moveDown/"+t).then(e).catch(r)}function j(t,e,r){o["a"].delete("/private/server/api/"+t).then(e).catch(r)}function G(t,e){o["a"].get("/private/server/api/template").then(t).catch(e)}function k(t,e,r){o["a"].get("/private/server/api/template/"+t).then(e).catch(r)}function M(t,e,r){o["a"].post("/private/server/api/template",t).then(e).catch(r)}function T(t,e,r){o["a"].delete("/private/server/api/template/"+t).then(e).catch(r)}},"970c":function(t,e,r){},a5d6:function(t,e,r){"use strict";r.r(e);var o=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticStyle:{width:"98%","max-width":"1240px",padding:"10px 0",margin:"auto"}},[r("div",{staticStyle:{display:"flex","align-items":"center","padding-bottom":"8px"}},[r("div",[r("b",[t._v(t._s(t.$t("ProjectName"))+": "+t._s(t.projectName))])]),r("div",{staticStyle:{"margin-left":"auto"}},[r("a",{staticClass:"alink",staticStyle:{color:"white"},attrs:{href:t.exportServerHost+"/client/index.html?id="+t.projectId+"&token="+t.sessionId,target:"_blank"}},[r("el-button",{attrs:{size:"medium",type:"primary",icon:"el-icon-position"}},[t._v(t._s(t.$t("OpenOnClient")))])],1),r("el-button",{staticStyle:{"margin-left":"4px"},attrs:{size:"medium",type:"primary",icon:"el-icon-plus"},on:{click:function(e){t.dialogCreateGroupVisible=!0,t.groupDialogMode="edit",t.groupData={}}}},[t._v("\n\t\t\t\t"+t._s(t.$t("NewGroup"))+"\n\t\t\t")])],1)]),r("el-container",[r("el-container",[r("el-aside",{staticStyle:{"max-width":"450px","min-width":"220px"},attrs:{width:"25%"}},[r("div",{staticStyle:{display:"flex","align-items":"center"}},[r("div",[r("b",[t._v(t._s(t.$t("GroupList")))])])]),r("ul",{directives:[{name:"loading",rawName:"v-loading",value:t.groupsLoading,expression:"groupsLoading"}],staticClass:"group-items"},[null==t.groups||0==t.groups.length?r("li",{staticStyle:{color:"#777"}},[t._v(t._s(t.$t("ThereIsNoGroupPleaseAdd")))]):t._e(),t._l(t.groups,(function(e,o){return r("li",{key:o,class:{current:t.selectGroupId==e.groupId},on:{click:function(r){return t.loadApis(e)}}},[r("span",{staticClass:"group-item"},[t._v(t._s(e.name))])])}))],2)]),r("el-main",{staticStyle:{padding:"0 0 0 15px"}},[t.group?r("div",[r("div",[r("table",{staticStyle:{width:"100%"},attrs:{cellspacing:"10"}},[r("tr",[r("td",{staticClass:"td-item",attrs:{width:"100px"}},[t._v(t._s(t.$t("GroupName")))]),r("td",[t._v(t._s(t.group.name))])]),r("tr",[r("td",{staticClass:"td-item"},[t._v(t._s(t.$t("GroupSummary")))]),r("td",[t._v(t._s(t.group.summary))])]),r("tr",[r("td",{staticClass:"td-item",attrs:{valign:"top"}},[t._v(t._s(t.$t("GroupDescription")))]),r("td",{domProps:{innerHTML:t._s(t.group.description)}})]),null!=t.group.externalDocs&&"{}"!=t.group.externalDocs?r("tr",[r("td",{staticClass:"td-item",attrs:{valign:"top"}},[t._v(t._s(t.$t("ExtDocsDesc")))]),r("td",[null!=JSON.parse(t.group.externalDocs).description?r("div",{domProps:{innerHTML:t._s(JSON.parse(t.group.externalDocs).description)}}):t._e(),null!=JSON.parse(t.group.externalDocs).url?r("a",{staticClass:"alink",staticStyle:{"margin-left":"0"},attrs:{href:JSON.parse(t.group.externalDocs).url,target:"_blank"}},[t._v("\n\t\t\t\t\t\t\t\t\t\t"+t._s(JSON.parse(t.group.externalDocs).url)+"\n\t\t\t\t\t\t\t\t\t")]):t._e()])]):t._e(),r("tr",[r("td",{staticClass:"td-item",attrs:{valign:"top"}},[t._v(t._s(t.$t("Operation")))]),r("td",[r("el-button",{attrs:{size:"mini"},on:{click:function(e){return t.groupMoveUp(t.group.groupId)}}},[t._v(t._s(t.$t("MoveUp")))]),r("el-button",{attrs:{size:"mini"},on:{click:function(e){return t.groupMoveDown(t.group.groupId)}}},[t._v(t._s(t.$t("MoveDown")))]),r("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(e){return t.showGroupUpdateDialog(t.group)}}},[t._v(t._s(t.$t("ModifyGroup")))]),r("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(e){return t.groupDeleteSubmit(t.group.groupId)}}},[t._v(t._s(t.$t("DeleteGroup")))])],1)])])]),r("div",{staticStyle:{"box-shadow":"0 1px 4px rgba(0, 21, 41, 0.08)",height:"2px","border-bottom":"1px solid #EEE"}}),r("div",{staticStyle:{padding:"10px 0",display:"flex","align-items":"center"}},[r("div",[r("b",[t._v(t._s(t.$t("ApiList")))])]),r("div",{staticStyle:{"margin-left":"auto"}},[r("a",{staticStyle:{margin:"0 10px"},attrs:{href:"#/index/post/project/api/"+t.projectId+"/"+t.group.groupId}},[r("el-button",{attrs:{size:"mini",type:"primary"}},[t._v(t._s(t.$t("NewApi")))])],1)])]),t._l(t.apis,(function(e){return r("div",{key:e.apiId,staticStyle:{"margin-bottom":"10px"}},[r("div",{class:["api",e.method]},[r("div",{class:["api-header",1==e.deprecated||"true"==e.deprecated?"text-through":""],on:{click:function(t){e.show=!e.show}}},[r("div",{staticClass:"api-method"},[t._v(t._s(e.method))]),r("div",{staticClass:"api-path-summary"},[1==e.deprecated||"true"==e.deprecated?r("span",[r("b",[t._v("("+t._s(t.$t("Deprecated"))+")")])]):t._e(),t._v("\n\t\t\t\t\t\t\t\t\t"+t._s(e.path)+"\n\t\t\t\t\t\t\t\t")]),r("div",{staticClass:"api-path-summary"},[t._v(t._s(e.title))]),r("div",{staticStyle:{"margin-left":"auto"}},[r("i",{directives:[{name:"show",rawName:"v-show",value:!e.show,expression:"!api.show"}],staticClass:"el-icon-arrow-right"}),r("i",{directives:[{name:"show",rawName:"v-show",value:e.show,expression:"api.show"}],staticClass:"el-icon-arrow-down"})])]),r("div",{staticStyle:{padding:"5px 10px","text-align":"right"},on:{click:function(t){e.show=!e.show}}},[r("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(r){return t.apiDeleteSubmit(e.apiId)}}},[t._v(t._s(t.$t("Delete")))]),r("a",{staticStyle:{margin:"0 10px"},attrs:{href:"#/index/put/project/api/"+t.projectId+"/"+e.groupId+"/"+e.apiId}},[r("el-button",{attrs:{size:"mini",type:"primary"}},[t._v(t._s(t.$t("Modify")))])],1),r("el-button",{attrs:{size:"mini"},on:{click:function(r){return t.apiMoveUp(e.apiId)}}},[t._v(t._s(t.$t("MoveUp")))]),r("el-button",{attrs:{size:"mini"},on:{click:function(r){return t.apiMoveDown(e.apiId)}}},[t._v(t._s(t.$t("MoveDown")))])],1),r("div",{directives:[{name:"show",rawName:"v-show",value:e.show,expression:"api.show"}]},[r("div",{staticStyle:{padding:"10px","background-color":"white"}},[e.description?r("div",{domProps:{innerHTML:t._s(e.description.replace(/\n/g,"<br>"))}}):t._e(),t._l(e.additional,(function(e,o){return r("div",{key:o},[r("div",[r("b",[t._v(t._s(e.title))])]),e.description?r("div",{domProps:{innerHTML:t._s(e.description.replace(/\n/g,"<br>"))}}):t._e()])})),null!=e.externalDocs?r("div",[null!=e.externalDocs.description?r("div",{domProps:{innerHTML:t._s(e.externalDocs.description)}}):t._e(),null!=e.externalDocs.url?r("a",{staticClass:"alink",staticStyle:{"margin-left":"0"},attrs:{href:e.externalDocs.url,target:"_blank"}},[t._v(t._s(e.externalDocs.url))]):t._e()]):t._e()],2),r("div",{staticStyle:{padding:"10px"}},[r("div",{staticStyle:{display:"flex","align-items":"center"}},[r("div",{staticStyle:{"min-width":"60px"}},[r("b",[t._v(t._s(t.$t("Parameters")))])]),null!=e.consumes?r("div",{staticStyle:{"margin-left":"auto"}},[t._v("Consumes: "+t._s(e.consumes))]):t._e()])]),r("div",{staticStyle:{padding:"5px 10px","background-color":"white"}},[r("el-table",{staticStyle:{width:"100%"},attrs:{data:e.parameters,"row-key":"tableRowKeyId",border:"","default-expand-all":"","tree-props":{children:"items",hasChildren:"hasChildren"},"empty-text":t.$t("ThereIsNoNeedToRequestParameters")}},[r("el-table-column",{attrs:{prop:"required",label:t.$t("Required"),width:"100",align:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return["true"==e.row.required||1==e.row.required?r("span",[t._v(t._s(t.$t("True")))]):t._e(),"false"==e.row.required||0==e.row.required?r("span",[t._v(t._s(t.$t("False")))]):t._e()]}}],null,!0)}),r("el-table-column",{attrs:{prop:"in",label:t.$t("Position"),width:"120"}}),r("el-table-column",{attrs:{prop:"type",label:t.$t("Type"),width:"120"}}),r("el-table-column",{attrs:{prop:"name",label:t.$t("ParamName"),width:"300"}}),r("el-table-column",{attrs:{prop:"description",label:t.$t("ParamDescription")},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.description?r("div",{domProps:{innerHTML:t._s(e.row.description)}}):t._e(),r("div",{staticClass:"desc-constraint"},[e.row.def?r("span",[t._v(t._s(t.$t("Default"))+": "+t._s(e.row.def))]):t._e(),e.row.minLength?r("span",[t._v(t._s(t.$t("MinLength"))+": "+t._s(e.row.minLength))]):t._e(),e.row.maxLength?r("span",[t._v(t._s(t.$t("MaxLength"))+": "+t._s(e.row.maxLength))]):t._e(),e.row.minimum?r("span",[t._v(t._s(t.$t("Minimum"))+": "+t._s(e.row.minimum))]):t._e(),e.row.maximum?r("span",[t._v(t._s(t.$t("Maximum"))+": "+t._s(e.row.maximum))]):t._e(),e.row.enums?r("span",[t._v(t._s(t.$t("Enums"))+": "+t._s(e.row.enums))]):t._e(),e.row.pattern?r("span",[t._v(t._s(t.$t("Pattern"))+": "+t._s(e.row.pattern))]):t._e()])]}}],null,!0)})],1),e.body&&""!=e.body?r("div",[r("el-input",{staticStyle:{border:"0"},attrs:{type:"textarea",autosize:{minRows:1}},model:{value:e.body,callback:function(r){t.$set(e,"body",r)},expression:"api.body"}})],1):t._e()],1),r("div",{staticStyle:{padding:"10px"}},[r("div",{staticStyle:{display:"flex","align-items":"center"}},[r("div",{staticStyle:{"min-width":"60px"}},[r("b",[t._v(t._s(t.$t("Responses")))])]),null!=e.produces?r("div",{staticStyle:{"margin-left":"auto"}},[t._v("Produces: "+t._s(e.produces))]):t._e()])]),r("div",{staticStyle:{padding:"5px 10px","background-color":"white"}},t._l(e.responses,(function(e,o){return r("div",{key:o},[r("p",[t._v(t._s(t.$t("Status"))+": "+t._s(e.status)+" "+t._s(t.$t("StatusMsg"))+": "+t._s(e.msg))]),r("el-table",{staticStyle:{width:"100%"},attrs:{data:e.data,"row-key":"tableRowKeyId",border:"","default-expand-all":"","tree-props":{children:"items",hasChildren:"hasChildren"}}},[r("el-table-column",{attrs:{prop:"in",label:t.$t("Position"),width:"120",align:"right"}}),r("el-table-column",{attrs:{prop:"type",label:t.$t("Type"),width:"100",align:"right"}}),r("el-table-column",{attrs:{prop:"name",label:t.$t("ParamName"),width:"300"}}),r("el-table-column",{attrs:{prop:"description",label:t.$t("ParamDescription")},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.description?r("div",{domProps:{innerHTML:t._s(e.row.description)}}):t._e()]}}],null,!0)})],1)],1)})),0)])])])}))],2):t._e()])],1)],1),r("el-dialog",{attrs:{title:"view"==t.groupDialogMode?t.$t("ModifyGroup"):t.$t("NewGroup"),visible:t.dialogCreateGroupVisible},on:{"update:visible":function(e){t.dialogCreateGroupVisible=e}}},[r("el-form",{ref:"groupEditForm",attrs:{model:t.groupData,rules:t.groupDataRules,"label-width":"100px"}},[r("el-form-item",{attrs:{label:t.$t("GroupName"),prop:"name"}},[r("el-input",{attrs:{placeholder:t.$t("EnterTheGroupName")},model:{value:t.groupData.name,callback:function(e){t.$set(t.groupData,"name",e)},expression:"groupData.name"}})],1),r("el-form-item",{attrs:{label:t.$t("GroupSummary"),prop:"summary"}},[r("el-input",{attrs:{placeholder:t.$t("EnterGroupSummary")},model:{value:t.groupData.summary,callback:function(e){t.$set(t.groupData,"summary",e)},expression:"groupData.summary"}})],1),r("el-form-item",{attrs:{label:t.$t("GroupRanking"),prop:"sorts"}},[r("el-input",{attrs:{type:"number",placeholder:t.$t("EnterGroupRanking")},model:{value:t.groupData.sorts,callback:function(e){t.$set(t.groupData,"sorts",e)},expression:"groupData.sorts"}})],1),r("el-form-item",{attrs:{label:t.$t("GroupDescription"),prop:"description"}},[r("el-input",{attrs:{type:"textarea",placeholder:t.$t("EnterGroupDescription")},model:{value:t.groupData.description,callback:function(e){t.$set(t.groupData,"description",e)},expression:"groupData.description"}})],1),r("el-form-item",{attrs:{label:t.$t("ExtDocsURL"),prop:"externalUrl"}},[r("el-input",{attrs:{placeholder:t.$t("EnterExtDocsURL")},model:{value:t.groupData.externalUrl,callback:function(e){t.$set(t.groupData,"externalUrl",e)},expression:"groupData.externalUrl"}})],1),r("el-form-item",{attrs:{label:t.$t("ExtDocsDesc"),prop:"externalDesc"}},[r("el-input",{attrs:{type:"textarea",placeholder:t.$t("EnterExtDocsDesc")},model:{value:t.groupData.externalDesc,callback:function(e){t.$set(t.groupData,"externalDesc",e)},expression:"groupData.externalDesc"}})],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(e){t.dialogCreateGroupVisible=!1}}},[t._v(t._s(t.$t("Cancel")))]),r("el-button",{directives:[{name:"show",rawName:"v-show",value:"edit"==t.groupDialogMode,expression:"groupDialogMode == 'edit'"}],attrs:{type:"primary"},on:{click:t.groupCreateSubmit}},[t._v(t._s(t.$t("Submit")))]),r("el-button",{directives:[{name:"show",rawName:"v-show",value:"view"==t.groupDialogMode,expression:"groupDialogMode == 'view'"}],attrs:{type:"primary"},on:{click:t.groupUpdateSubmit}},[t._v(t._s(t.$t("SubmitModify")))])],1)],1)],1)},a=[],n=(r("7f7f"),r("0db5")),i=r("4360"),s="edit",l="LAST_SELECT_GID",c={data:function(){return{exportServerHost:"",sessionId:i["a"].getters.sessionId,projectId:"",projectName:"项目的名称",dialogCreateGroupVisible:!1,groupDialogMode:s,groupData:{name:"",summary:"",sorts:0,description:"",externalUrl:null,externalDesc:null},groupDataRules:{name:[{required:!0,message:this.$t("EnterTheGroupName"),trigger:"blur"}],summary:[{required:!0,message:this.$t("EnterGroupSummary"),trigger:"blur"}]},groups:[],group:null,groupsLoading:!0,selectGroupId:null,apis:[]}},created:function(){var t=i["a"].getters.role;if("ROOT"!=t&&"SERVER"!=t)this.$router.push("/index");else{var e=this.$route.params.pid;if(null==e||""==e)return void this.$message.warning(this.$t("FailedToLoadTheProjectInvalidID"));this.projectId=e,this.loadProjectInfo(e),this.loadProjectGroups(e)}},methods:{loadProjectInfo:function(t){var e=this;Object(n["r"])(t,(function(t){var r=t.data;if(console.log("get project..."),console.log(r),200==r.code){if(null==r.data)return void e.$message.error(e.$t("FailedToLoadTheProjectInvalidID"));e.projectName=r.data.name}}),(function(t){e.$message.error(e.$t("FailedToGetProjectInfoSeeConsole")),console.log(t)}))},loadProjectGroups:function(t){var e=this;null!=t&&""!=t&&(this.groupsLoading=!0,Object(n["m"])(t,(function(t){var r=t.data;if(console.log("get project list..."),console.log(r),200==r.code){e.groups=r.data;var o=sessionStorage.getItem(l),a=!0;if(null!=o&&""!=o)for(var n=0;n<e.groups.length;n++)if(e.groups[n].groupId==o){e.loadApis(e.groups[n]),a=!1;break}a&&e.groups.length>0&&e.loadApis(e.groups[0]),e.groupsLoading=!1}}),(function(t){e.$message.error(e.$t("FailedToGetGroupInfoSeeConsole")),console.log(t)})))},loadApis:function(t){console.log(t);var e=t.groupId;this.selectGroupId=e,this.group=t,sessionStorage.setItem(l,e),this.findApisAndLoad(e)},findApisAndLoad:function(t){var e=this;null!=t&&""!=t&&(this.apis=[],Object(n["l"])(t,(function(t){var r=t.data;if(console.log("get API list..."),console.log(r),200==r.code)for(var o=0;o<r.data.length;o++){var a=r.data[o];if(a.show=!1,null!=a.additional&&""!=a.additional&&(a.additional=JSON.parse(a.additional)),null!=a.externalDocs&&""!=a.externalDocs&&(a.externalDocs=JSON.parse(a.externalDocs)),null!=a.parameters){a.parameters=JSON.parse(a.parameters);for(var n=0;n<a.parameters.length;n++)e.recursionCreateTableRandomRowKey(a.parameters[n])}else a.parameters=[];if(null!=a.responses){var i=JSON.parse(a.responses);null!=i&&i.length>0&&(void 0==i[0].status||void 0==i[0].data)?a.responses=[{status:200,msg:"ok",data:i}]:a.responses=i;for(var s=0;s<a.responses.length;s++){var l=a.responses[s].data;for(n=0;n<l.length;n++)e.recursionCreateTableRandomRowKey(l[n])}}else a.responses=[];e.apis.push(a)}e.dialogCreateGroupVisible=!1}),(function(t){e.$message.error(e.$t("FailedToGetGroupInfoSeeConsole")),console.log(t),e.dialogCreateGroupVisible=!1})))},groupCreateSubmit:function(){var t=this;this.$refs.groupEditForm.validate((function(e){if(e){var r={};if(r.projectId=t.projectId,r.sorts=t.groupData.sorts,r.name=t.groupData.name,r.summary=t.groupData.summary,r.description=t.groupData.description,null!=t.groupData.externalUrl&&""!=t.groupData.externalUrl||null!=t.groupData.externalDesc&&""!=t.groupData.externalDesc){var o={};null!=t.groupData.externalUrl&&""!=t.groupData.externalUrl&&(o.url=t.groupData.externalUrl),null!=t.groupData.externalDesc&&""!=t.groupData.externalDesc&&(o.description=t.groupData.externalDesc),r.externalDocs=JSON.stringify(o)}Object(n["x"])(r,(function(e){var r=e.data;console.log("new group..."),console.log(r),200==r.code&&(t.loadProjectGroups(t.projectId),t.groupData={}),t.dialogCreateGroupVisible=!1}),(function(e){t.$message.error(t.$t("FailedToAddSeeConsole")),console.log(e),t.dialogCreateGroupVisible=!1}))}}))},showGroupUpdateDialog:function(t){this.dialogCreateGroupVisible=!0,this.groupDialogMode="view";var e={groupId:t.groupId,name:t.name,summary:t.summary,sorts:t.sorts,description:t.description};if(null!=t.externalDocs&&""!=t.externalDocs){var r=JSON.parse(t.externalDocs);e.externalUrl=r.url,e.externalDesc=r.description}this.groupData=e},groupUpdateSubmit:function(){var t=this;this.$refs.groupEditForm.validate((function(e){if(e){var r={};if(r.projectId=t.projectId,r.groupId=t.groupData.groupId,r.name=t.groupData.name,r.summary=t.groupData.summary,r.sorts=t.groupData.sorts,r.description=t.groupData.description,null!=t.groupData.externalUrl&&""!=t.groupData.externalUrl||null!=t.groupData.externalDesc&&""!=t.groupData.externalDesc){var o={};null!=t.groupData.externalUrl&&""!=t.groupData.externalUrl&&(o.url=t.groupData.externalUrl),null!=t.groupData.externalDesc&&""!=t.groupData.externalDesc&&(o.description=t.groupData.externalDesc),r.externalDocs=JSON.stringify(o)}Object(n["v"])(r,(function(e){var o=e.data;console.log("modify group ..."),console.log(o),200==o.code&&(t.loadProjectGroups(t.projectId),t.group=r,t.$message.success(t.$t("ModifySuccess"))),t.dialogCreateGroupVisible=!1}),(function(e){t.$message.error(t.$t("FailedToModifySeeConsole")),console.log(e),t.dialogCreateGroupVisible=!1}))}}))},groupDeleteSubmit:function(t){var e=this;this.$confirm(this.$t("DeleteConfirm"),this.$t("Tips"),{confirmButtonText:this.$t("Confirm"),cancelButtonText:this.$t("Cancel"),type:"warning"}).then((function(){Object(n["f"])(t,(function(t){var r=t.data;console.log("delete group ..."),console.log(r),200==r.code&&(e.$message.success(e.$t("DeleteSuccess")),location.reload())}),(function(t){e.$message.error(e.$t("FailedToModifySeeConsole")),console.log(t)}))})).catch((function(){}))},groupMoveUp:function(t){var e=this;Object(n["b"])(t,(function(t){var r=t.data;console.log("group move up..."),console.log(r),200==r.code&&(e.$message.success(e.$t("MoveSuccess")),e.loadProjectGroups(e.projectId))}),(function(t){e.$message.error(e.$t("MoveFailedSeeConsole")),console.log(t)}))},groupMoveDown:function(t){var e=this;Object(n["a"])(t,(function(t){var r=t.data;console.log("group move down..."),console.log(r),200==r.code&&(e.$message.success(e.$t("MoveSuccess")),e.loadProjectGroups(e.projectId))}),(function(t){e.$message.error(e.$t("MoveFailedSeeConsole")),console.log(t)}))},apiDeleteSubmit:function(t){var e=this;this.$confirm(this.$t("DeleteConfirm"),this.$t("Tips"),{confirmButtonText:this.$t("Confirm"),cancelButtonText:this.$t("Cancel"),type:"warning"}).then((function(){Object(n["g"])(t,(function(t){var r=t.data;console.log("delete API..."),console.log(r),200==r.code&&(e.$message.success(e.$t("DeleteSuccess")),e.findApisAndLoad(e.group.groupId))}),(function(t){e.$message.error(e.$t("FailedToModifySeeConsole")),console.log(t)}))})).catch((function(){}))},apiMoveUp:function(t){var e=this;Object(n["d"])(t,(function(t){var r=t.data;console.log("API move up..."),console.log(r),200==r.code&&(e.$message.success(e.$t("MoveSuccess")),e.findApisAndLoad(e.group.groupId))}),(function(t){e.$message.error(e.$t("MoveFailedSeeConsole")),console.log(t)}))},apiMoveDown:function(t){var e=this;Object(n["c"])(t,(function(t){var r=t.data;console.log("API move down..."),console.log(r),200==r.code&&(e.$message.success(e.$t("MoveSuccess")),e.findApisAndLoad(e.group.groupId))}),(function(t){e.$message.error(e.$t("MoveFailedSeeConsole")),console.log(t)}))},recursionCreateTableRandomRowKey:function(t){if(t.tableRowKeyId="rowkey-"+Math.random(),null==t.items)return t;for(var e=0;e<t.items.length;e++)this.recursionCreateTableRandomRowKey(t.items[e])}}},u=c,p=(r("c13a"),r("2877")),d=Object(p["a"])(u,o,a,!1,null,"71a97170",null);e["default"]=d.exports},c13a:function(t,e,r){"use strict";var o=r("970c"),a=r.n(o);a.a}}]);
//# sourceMappingURL=chunk-7fb4d854.62b3187e.js.map