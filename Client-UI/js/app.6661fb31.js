(function(e){function t(t){for(var a,o,l=t[0],n=t[1],c=t[2],u=0,d=[];u<l.length;u++)o=l[u],Object.prototype.hasOwnProperty.call(r,o)&&r[o]&&d.push(r[o][0]),r[o]=0;for(a in n)Object.prototype.hasOwnProperty.call(n,a)&&(e[a]=n[a]);p&&p(t);while(d.length)d.shift()();return i.push.apply(i,c||[]),s()}function s(){for(var e,t=0;t<i.length;t++){for(var s=i[t],a=!0,l=1;l<s.length;l++){var n=s[l];0!==r[n]&&(a=!1)}a&&(i.splice(t--,1),e=o(o.s=s[0]))}return e}var a={},r={app:0},i=[];function o(t){if(a[t])return a[t].exports;var s=a[t]={i:t,l:!1,exports:{}};return e[t].call(s.exports,s,s.exports,o),s.l=!0,s.exports}o.m=e,o.c=a,o.d=function(e,t,s){o.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:s})},o.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},o.t=function(e,t){if(1&t&&(e=o(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var s=Object.create(null);if(o.r(s),Object.defineProperty(s,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var a in e)o.d(s,a,function(t){return e[t]}.bind(null,a));return s},o.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return o.d(t,"a",t),t},o.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},o.p="";var l=window["webpackJsonp"]=window["webpackJsonp"]||[],n=l.push.bind(l);l.push=t,l=l.slice();for(var c=0;c<l.length;c++)t(l[c]);var p=n;i.push([0,"chunk-vendors"]),s()})({0:function(e,t,s){e.exports=s("56d7")},"034f":function(e,t,s){"use strict";var a=s("64a9"),r=s.n(a);r.a},"56d7":function(e,t,s){"use strict";s.r(t);s("cadf"),s("551c"),s("f751"),s("097d");var a=s("2b0e"),r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-container",{attrs:{id:"app"}},[a("div",{attrs:{id:"mobile-bar"}},[a("i",{class:[e.isAsideShow?"el-icon-s-fold":"el-icon-s-unfold","menu-button"],on:{click:e.showAside}}),a("div",{staticClass:"logo"},[a("a",{attrs:{href:"/"}},[e._v("Orion-API-Manager")])])]),a("el-header",{attrs:{id:"pc-bar"}},[a("a",{attrs:{id:"logo",href:"/"}},[a("img",{attrs:{src:s("cf05"),alt:""}}),a("span",[e._v("Orion-API-Manager")])]),a("ul",{attrs:{id:"nav"}},[a("li",{staticStyle:{width:"450px"}},[a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"文档的URL,代理请求可加上P: 示例:P:https://xxx.xxx/xx"},model:{value:e.fileUrl,callback:function(t){e.fileUrl=t},expression:"fileUrl"}},[a("el-button",{attrs:{slot:"append"},on:{click:e.getProjectFromUrl},slot:"append"},[e._v("加载")])],1)],1),a("li",[a("a",{on:{click:function(t){return e.$refs.readFile.click()}}},[e._v("本地加载")]),a("input",{ref:"readFile",staticStyle:{display:"none"},attrs:{type:"file",accept:".json,.txt"},on:{change:e.getProjectFromFile}})]),a("li",{staticStyle:{"text-align":"center"}},[a("a",{on:{click:function(t){e.drawer=!0}}},[e._v("项目切换")])])])]),a("el-drawer",{attrs:{title:"项目列表",direction:"btt",visible:e.drawer},on:{"update:visible":function(t){e.drawer=t}}},[a("div",e._l(e.projects,(function(t){return a("p",{key:t.key},[a("a",{attrs:{href:["./index.html?id="+t.key]}},[e._v(e._s(t.name))])])})),0)]),a("el-container",[a("el-aside",{directives:[{name:"show",rawName:"v-show",value:e.isAsideShow,expression:"isAsideShow"}],attrs:{id:"aside",width:"25%"}},[a("ul",{attrs:{id:"menu-body"}},[a("li",{directives:[{name:"show",rawName:"v-show",value:!e.isMainShow,expression:"!isMainShow"}],staticStyle:{"text-align":"center"}},[a("a",{on:{click:function(t){e.drawer=!0}}},[e._v("项目切换")])]),null==e.projectId?a("div",{staticStyle:{"text-align":"center","padding-top":"30px"}},[e._v("尚未加载项目")]):e._e(),null==e.projectId||e.apiGroups?e._e():a("div",{staticStyle:{"text-align":"center","padding-top":"30px"}},[e._v("尚无API")]),e._l(e.apiGroups,(function(t,s){return a("li",{key:t.groupId},[a("span",{class:{current:e.menuCurrent==s},on:{click:function(t){return e.currentActive(s,-1)}}},[e._v(e._s(t.name))]),t.apis?a("ul",{staticClass:"menu-sub"},e._l(t.apis,(function(t,r){return a("li",{key:t.apiId,class:{current:e.menuCurrent==s&&e.menuSubCurrent==r},on:{click:function(t){return e.currentActive(s,r)}}},[e._v("\n\t\t\t\t\t\t\t"+e._s(t.title)+"\n\t\t\t\t\t\t")])})),0):e._e()])}))],2)]),a("el-main",{directives:[{name:"show",rawName:"v-show",value:e.isMainShow,expression:"isMainShow"}],attrs:{id:"main"}},[a("div",{directives:[{name:"show",rawName:"v-show",value:-1==e.menuCurrent,expression:"menuCurrent == -1"}]},[e.project.name?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("项目")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v("\n\t\t\t\t\t\t"+e._s(e.project.name)+"\n\t\t\t\t\t\t"),a("span",{staticStyle:{color:"#666","font-size":"13px"}},[e._v(e._s(e.project.versions))])])],1):e._e(),e.project.description?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("描述")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.project.description))])],1):e._e(),e.project.schemes?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("请求协议")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.project.schemes))])],1):e._e(),e.project.host?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("主机地址")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.project.host))])],1):e._e(),e.project.basePath?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("根路径")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.project.basePath))])],1):e._e(),e.project.externalDocs?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("附加文档")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v("\n\t\t\t\t\t\t"+e._s(e.project.externalDocs.description)+"\n\t\t\t\t\t\t"),a("br"),a("a",{staticStyle:{color:"#42b983"},attrs:{href:e.project.externalDocs.url,target:"_blank"}},[e._v(e._s(e.project.externalDocs.url))])])],1):e._e(),e.project.contactName?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("联系人")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.project.contactName))])],1):e._e(),e.project.contactInfo?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("联系信息")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.project.contactInfo))])],1):e._e(),e.project.lastTime?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("时间")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.project.lastTime))])],1):e._e()],1),a("div",{directives:[{name:"show",rawName:"v-show",value:-1!=e.menuCurrent&&-1==e.menuSubCurrent,expression:"menuCurrent != -1 && menuSubCurrent == -1"}]},[a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("分组名称")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.group.name))])],1),a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("分组简介")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.group.summary))])],1),a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[e.group.description?a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("分组描述")])]):e._e(),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v(e._s(e.group.description))])],1),e.group.externalDocs?a("el-row",{staticClass:"mb10px",attrs:{gutter:15}},[a("el-col",{staticClass:"xs-left-sm-rigth",attrs:{xs:24,sm:6,md:4}},[a("b",[e._v("附加文档")])]),a("el-col",{attrs:{xs:24,sm:18,md:20}},[e._v("\n\t\t\t\t\t\t"+e._s(e.group.externalDocs.description)+"\n\t\t\t\t\t\t"),a("br"),a("a",{staticStyle:{color:"#42b983"},attrs:{href:e.group.externalDocs.url,target:"_blank"}},[e._v(e._s(e.group.externalDocs.url))])])],1):e._e()],1),a("div",{directives:[{name:"show",rawName:"v-show",value:-1!=e.menuSubCurrent,expression:"menuSubCurrent != -1"}]},[a("div",{staticClass:"api-body",class:["api-body-"+e.api.method]},[e.api.deprecated?a("div",{staticClass:"prem05"},[e._v(e._s(e.api.deprecated?"该接口已过时!!!":""))]):e._e(),a("div",{staticClass:"api-header",class:["api-header-"+e.api.method],style:e.api.deprecated?"text-decoration: line-through;":""},[a("div",{staticClass:"api-header-item"},[e._v(e._s(e.api.title))]),a("div",{staticClass:"api-header-item"},[e._v("请求方法: "+e._s(e.api.methodUpperCase))]),a("div",{staticClass:"api-header-item",staticStyle:{display:"flex","flex-wrap":"wrap"}},[a("div",{staticClass:"flexCenter"},[e._v("请求URL: ")]),a("div",{staticClass:"flexCenter",staticStyle:{width:"90%"}},[a("el-input",{attrs:{placeholder:"请输入内容",size:"mini"},model:{value:e.requestUrl,callback:function(t){e.requestUrl=t},expression:"requestUrl"}},[a("el-select",{staticStyle:{width:"6rem",color:"#222"},attrs:{slot:"prepend",placeholder:"请选择"},slot:"prepend",model:{value:e.api.scheme,callback:function(t){e.$set(e.api,"scheme",t)},expression:"api.scheme"}},e._l(e.project.schemes,(function(e,t){return a("el-option",{key:t,attrs:{label:e,value:e}})})),1),a("el-button",{staticStyle:{color:"#222"},attrs:{slot:"append"},on:{click:e.copy},slot:"append"},[e._v("Copy")]),a("el-button",{staticStyle:{color:"#222"},attrs:{slot:"append"},on:{click:e.copyPath},slot:"append"},[e._v("CopyPath")])],1)],1)])]),a("div",{staticClass:"prem05 background-color-white"},[a("div",{domProps:{innerHTML:e._s(e.api.description)}}),e.api.externalDocs?a("div",{staticStyle:{display:"flex","flex-wrap":"wrap","margin-top":"0.5rem"}},[a("div",{staticStyle:{"padding-right":"0.5rem"}},[e._v("附加文档")]),a("div",[e._v("\n\t\t\t\t\t\t\t\t"+e._s(e.api.externalDocs.description)+"\n\t\t\t\t\t\t\t\t"),a("br"),a("a",{staticStyle:{color:"#42b983"},attrs:{href:e.api.externalDocs.url,target:"_blank"}},[e._v(e._s(e.api.externalDocs.url))])])]):e._e()]),e.api.parameters?a("div",{staticClass:"api-body-params"},[a("div",{staticClass:"api-body-param-header"},[a("div",{staticStyle:{display:"flex"}},[e._v("请求参数")]),a("div",{staticStyle:{display:"flex","align-items":"first baseline"}},[e._v("\n\t\t\t\t\t\t\t\t请求类型 \n\t\t\t\t\t\t\t\t"),a("el-select",{attrs:{size:"mini"},model:{value:e.api.requestType,callback:function(t){e.$set(e.api,"requestType",t)},expression:"api.requestType"}},[a("el-option",{attrs:{label:"x-www-form-urlencoded",value:"x-www-form-urlencoded"}}),a("el-option",{attrs:{label:"application/json",value:"application/json"}})],1)],1)]),a("div",{staticClass:"api-body-param-path plrrem05"},[a("el-table",{ref:"requestParamsTable",staticStyle:{width:"100%"},attrs:{data:e.api.parameters,"row-key":"description","default-expand-all":"","tree-props":{children:"items",hasChildren:"hasChildren"},"tooltip-effect":"dark","empty-text":"无需请求参数"}},[a("el-table-column",{attrs:{prop:"join",label:"加入",width:"50",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[null!=t.row.join?a("el-checkbox",{attrs:{id:"api-parameters-join-"+t.$index},nativeOn:{click:function(s){return s.preventDefault(),e.changeCheckBoxSelect("api-parameters-join-"+t.$index,t.row)}},model:{value:t.row.join,callback:function(s){e.$set(t.row,"join",s)},expression:"scope.row.join"}}):e._e()]}}],null,!1,452776247)}),a("el-table-column",{attrs:{prop:"required",label:"必填",width:"50"}}),a("el-table-column",{attrs:{prop:"in",label:"参数位置",width:"100"}}),a("el-table-column",{attrs:{prop:"type",label:"参数类型",width:"100"}}),a("el-table-column",{attrs:{prop:"name",label:"参数名称","min-width":"100"}}),a("el-table-column",{attrs:{prop:"value",label:"参数值","min-width":"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[null!=t.row.join?a("el-input",{attrs:{placeholder:"请输入参数值"},model:{value:t.row.value,callback:function(s){e.$set(t.row,"value",s)},expression:"scope.row.value"}}):e._e()]}}],null,!1,2201622814)}),a("el-table-column",{attrs:{prop:"description",label:"参数描述","min-width":"150","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"contains",label:"参数说明","min-width":"150","show-overflow-tooltip":""}})],1)],1)]):e._e(),a("div",{staticClass:"api-body-result"},e._l(e.api.responses,(function(t){return a("div",[a("div",{staticClass:"api-body-result-header"},[a("div",{staticStyle:{display:"flex"}},[e._v("返回结果－状态码: "+e._s(t.status))]),a("div",{staticStyle:{display:"flex","align-items":"first baseline"}},[e._v(e._s(t.msg)+" ")])]),a("div",{staticClass:"plrrem05"},[a("el-table",{attrs:{data:t.data,"row-key":"description",border:"","default-expand-all":"","tree-props":{children:"items",hasChildren:"hasChildren"}}},[a("el-table-column",{attrs:{prop:"type",label:"参数类型",width:"200"}}),a("el-table-column",{attrs:{prop:"name",label:"参数名称",width:"200"}}),a("el-table-column",{attrs:{prop:"description",label:"参数描述"}})],1)],1)])})),0),a("div",{staticClass:"api-body-response-header"},[a("div",{staticStyle:{display:"flex","align-items":"first baseline"}},[a("span",{directives:[{name:"show",rawName:"v-show",value:e.api.isSxecute,expression:"api.isSxecute"}]},[e._v("响应结果")])]),a("div",{staticStyle:{display:"flex","flex-wrap":"wrap","align-items":"center"}},[a("div",{staticStyle:{"margin-right":"0.7rem"}},[a("el-checkbox",{model:{value:e.api.proxy,callback:function(t){e.$set(e.api,"proxy",t)},expression:"api.proxy"}},[e._v("使用代理")])],1),a("div",[a("el-button",{staticStyle:{"background-color":"#42b983",color:"white"},attrs:{loading:e.api.executing},on:{click:e.execute}},[e._v(e._s(e.api.executing?"请求中...":"执行请求"))])],1)])]),a("div",{directives:[{name:"show",rawName:"v-show",value:e.api.isSxecute,expression:"api.isSxecute"}],staticClass:"api-body-response-body"},[a("el-tabs",{staticStyle:{width:"100%"},model:{value:e.api.responseTags,callback:function(t){e.$set(e.api,"responseTags",t)},expression:"api.responseTags"}},[a("el-tab-pane",{attrs:{label:"美化数据",name:"format"}},[a("json-viewer",{attrs:{"expand-depth":10,value:e.api.response}})],1),a("el-tab-pane",{attrs:{label:"原始数据",name:"original"}},[a("pre",[a("code",[e._v(e._s(e.api.response))])])]),a("el-tab-pane",{staticStyle:{color:"#409eff"},attrs:{label:"header信息",name:"header"}},[e.api.requestHenders?a("el-divider",{attrs:{"content-position":"left"}},[e._v("请求头信息")]):e._e(),e._l(e.api.requestHenders,(function(t,s){return a("div",{staticStyle:{display:"flex"}},[a("div",[e._v(e._s(s))]),a("div",{staticStyle:{margin:"0 0.5rem"}},[e._v(":")]),a("div",{staticStyle:{color:"#ff7de9"}},[e._v(e._s(t))])])})),e.api.responseHeaders?a("el-divider",{attrs:{"content-position":"left"}},[e._v("响应头信息")]):e._e(),e._l(e.api.responseHeaders,(function(t,s){return a("div",{staticStyle:{display:"flex"}},[a("div",[e._v(e._s(s))]),a("div",{staticStyle:{margin:"0 0.5rem"}},[e._v(":")]),a("div",{staticStyle:{color:"#ff7de9"}},[e._v(e._s(t))])])}))],2)],1)],1)])])])],1)],1)},i=[];s("3b2b"),s("a481"),s("7f7f"),s("4f37"),s("673e");function o(e,t){return decodeURIComponent((new RegExp("[?|&]"+e+"=([^&;]+?)(&|#|;|$)").exec(t)||[,""])[1].replace(/\+/g,"%20"))||null}var l=s("349e"),n=s.n(l),c=s("bc3a"),p=s.n(c),u="http://localhost:8686",d={components:{JsonViewer:n.a},data:function(){return{isAsideShow:!0,isMainShow:!0,drawer:!1,menuCurrent:-1,menuSubCurrent:-1,fileUrl:null,projectId:null,requestUrl:"",projects:[],apiGroups:[],apis:[],project:{},group:{},api:{requestType:"x-www-form-urlencoded",parameters:null,responses:null,proxy:!1,responseTags:"format",requestHenders:{},responseHeaders:{},response:{},isSxecute:!1,executing:!1}}},methods:{changeCheckBoxSelect:function(e,t){t.join=!t.join,t.join?(document.getElementById(e).classList.add("is-checked"),document.getElementById(e).children[0].classList.add("is-checked")):(document.getElementById(e).classList.remove("is-checked"),document.getElementById(e).children[0].classList.remove("is-checked"))},showAside:function(){!1===this.isAsideShow?(this.isAsideShow=!0,this.isMainShow=!1):(this.isAsideShow=!1,this.isMainShow=!0)},closeAside:function(){!1===this.isAsideShow?(this.isAsideShow=!0,this.isMainShow=!1):(this.isAsideShow=!1,this.isMainShow=!0)},currentActive:function(e,t){this.menuCurrent=e,this.menuSubCurrent=t,!1===this.isMainShow&&(this.isMainShow=!0,this.isAsideShow=!1),this.group=this.apiGroups[e];var s=this.group.apis[t];if(null!=s){var a=this.api;if(a.title=s.title,a.deprecated=s.deprecated,a.method=s.method,a.methodUpperCase=s.method.toUpperCase(),a.scheme=this.project.schemes[0],this.requestUrl=(this.project.host||"")+(this.project.basePath||"")+s.path,a.description=s.description,a.externalDocs=s.externalDocs,a.parameters=s.parameters,a.version=s.version,null!=a.parameters)for(var r=0;r<a.parameters.length;r++){a.parameters[r].join="true"==a.parameters[r].required;var i="";null==a.version?(null!=a.parameters[r].default&&""!=a.parameters[r].default&&(i+="默认值: "+a.parameters[r].default+"　"),null!=a.parameters[r].enum&&""!=a.parameters[r].enum&&(i+="枚举值: "+a.parameters[r].enum+"　"),null!=a.parameters[r].explain&&""!=a.parameters[r].explain&&(null!=a.parameters[r].explain.min&&""!=a.parameters[r].explain.min&&(i+="最小: "+a.parameters[r].explain.min+"　"),null!=a.parameters[r].explain.max&&""!=a.parameters[r].explain.max&&(i+="最大: "+a.parameters[r].explain.max+"　"),null!=a.parameters[r].explain.items&&""!=a.parameters[r].explain.items&&(a.parameters[r].items=a.parameters[r].explain.items)),null!=a.parameters[r].pattern&&""!=a.parameters[r].pattern&&(i+="正则表达式: "+a.parameters[r].pattern+"　")):(null!=a.parameters[r].def&&""!=a.parameters[r].def&&(i+="默认值: "+a.parameters[r].def+"　"),null!=a.parameters[r].enums&&""!=a.parameters[r].enums&&(i+="枚举值: "+a.parameters[r].enums+"　"),null!=a.parameters[r].minLength&&""!=a.parameters[r].minLength&&(i+="最小长度: "+a.parameters[r].minLength+"　"),null!=a.parameters[r].maxLength&&""!=a.parameters[r].maxLength&&(i+="最大长度: "+a.parameters[r].maxLength+"　"),null!=a.parameters[r].minValue&&""!=a.parameters[r].minValue&&(i+="最小值: "+a.parameters[r].minValue+"　"),null!=a.parameters[r].maxValue&&""!=a.parameters[r].maxValue&&(i+="最大值: "+a.parameters[r].maxValue+"　"),null!=a.parameters[r].regex&&""!=a.parameters[r].regex&&(i+="正则表达式: "+a.parameters[r].regex+"　")),""!=i&&(a.parameters[r].contains=i)}a.responses=s.responses,null==a.version&&null!=a.responses&&(a.responses=[{status:200,data:s.responses}]),a.isSxecute=!1}},loadProjectList:function(){var e=this;p.a.get(u+"/project/").then((function(t){var s=t.data;200===s.code?(console.log("加载项目列表成功!"),e.projects=s.data,e.api.proxy=!0):(console.log("加载项目列表失败:"),console.log(t))})).catch((function(e){console.log("请求项目列表失败:"),console.log(e)}))},getProject:function(e){if(null!=e&&""!=e){var t=this;p.a.get(u+"/project/getJson/"+e).then((function(e){var s=e.data;t.loadProject(s)})).catch((function(e){t.$notify.error({title:"加载失败",message:"加载项目信息失败,更多信息请查看控制台",position:"bottom-right"}),console.log(e)}))}},getProjectFromUrl:function(){if(""!==this.fileUrl.trim()){var e=this,t=e.fileUrl;"P"==t.charAt(0)&&":"==t.charAt(1)?(t=t.substring(2),p.a.get(u+"/proxy/project?url="+t).then((function(t){200==t.data.code?e.loadProject(JSON.parse(t.data.data)):(e.$notify.error({title:"请求失败",message:"代理请求项目信息失败,更多信息请查看控制台",position:"bottom-right"}),console.log(t))})).catch((function(t){e.$notify.error({title:"请求失败",message:"代理请求项目信息失败,更多信息请查看控制台",position:"bottom-right"}),console.log(t)}))):p.a.get(e.fileUrl).then((function(t){var s=t.data;e.loadProject(s)})).catch((function(t){e.$notify.error({title:"加载失败",message:"加载项目信息失败,更多信息请查看控制台",position:"bottom-right"}),console.log(t)}))}},getProjectFromFile:function(){var e=new FileReader,t=this.$refs.readFile.files[0];e.readAsText(t);var s=this;e.onload=function(e){try{s.loadProject(JSON.parse(e.target.result))}catch(t){s.$notify.error({title:"加载失败",message:"加载项目信息失败,更多信息请查看控制台",position:"bottom-right"}),console.log(t)}}},loadProject:function(e){null!=e&&"{}"!=JSON.stringify(e)&&(this.projectId=e.key,this.project.key=e.key,this.project.name=e.name,this.project.versions=e.versions,this.project.description=e.description,this.project.host=e.host,this.project.basePath=e.basePath,this.project.schemes=e.schemes,null!=e.schemes&&e.schemes.length>0&&(this.project.scheme=e.schemes[0]),this.project.contactName=e.contactName,this.project.contactInfo=e.contactInfo,this.project.externalDocs=e.externalDocs,this.project.lastTime=e.lastTime,this.apiGroups=e.content,this.$notify({title:"提示",message:"项目加载成功!",type:"success",position:"bottom-left",duration:1e3}))},copy:function(){var e=this.api.scheme+"://"+this.requestUrl;console.log("copy:data="+e),this.toCopy(e)},copyPath:function(){var e=this.requestUrl.url.replace(this.project.host+(this.project.basePath||""),"");console.log("copy-path:data="+e),this.toCopy(e)},toCopy:function(e){var t=document.createElement("input");t.value=e,document.body.appendChild(t),t.select(),document.execCommand("Copy"),this.$notify({title:"复制成功!",type:"success",position:"bottom-right"}),t.remove()},execute:function(){for(var e=this.api.requestType,t=this.api.method,s=this.api.scheme+"://"+this.requestUrl,a=this.api.proxy,r=null,i=null,o=null,l=this.api.parameters||{},n=0;n<l.length;n++)if(l[n].join){if(null==l[n].value&&"path"!=l[n].in)continue;switch(l[n].in.toLowerCase()){case"path":var c=new RegExp(":"+l[n].name,"g");s=s.replace(c,l[n].value||"");break;case"header":null==r&&(r={}),r[l[n].name]=l[n].value;break;case"formdata":null==o&&(o={}),o[l[n].name]=l[n].value;break;case"body":null==o&&(o={}),o[l[n].name]=l[n].value;break;default:null==i&&(i={}),i[l[n].name]=l[n].value}}var d=this;d.api.executing=!0;var m={};if(m.method=t,a){if(m.url=u+"/proxy/server",m.headers={},null!=r){for(var h in r)d.api.requestHenders[h]=r[h];m.headers["x-header"]=JSON.stringify(r)}m.headers["x-url"]=s}else if(m.url=s,null!=r)for(var h in m.headers=r,r)d.api.requestHenders[h]=r[h];if(null!=i&&(m.params=i),null!=o)if("x-www-form-urlencoded"==e)if(a)null==r&&(r={}),m.headers["x-type"]="x-www-form-urlencoded",m.data="x-wfubody="+JSON.stringify(o);else{var f="",v=!0;for(var h in o)v?v=!1:f+="&",f+=h+"="+o[h];m.data=f}else m.data=o;p()(m).then((function(e){for(var t in d.api.isSxecute=!0,d.api.executing=!1,d.api.response=e.data,d.api.responseHeaders.status=e.status,a&&null!=r?d.api.responseHeaders.tips="使用代理自定义的header可能不显示,你可以打开控制台查看服务器返回的header":delete d.api.responseHeaders.tips,e.headers)d.api.responseHeaders[t]=e.headers[t];console.log("request result:"),console.log(e)})).catch((function(e){if(d.api.isSxecute=!0,d.api.executing=!1,null!=e.response&&502==e.response.status)d.api.response=e.response.data;else{var t={tips:"更多信息请查看控制台!"};t.error=e,d.api.response=t}console.log("request error: "),console.log(e)}))}},mounted:function(){var e=this;e.isAsideShow=document.body.offsetWidth>768,window.onresize=function(){e.isAsideShow=document.body.offsetWidth>768,e.isMainShow=!0},this.loadProjectList(),this.projectId=o("id",window.location.href),null!=this.project&&""!==this.projectId&&this.getProject(this.projectId)}},m=d,h=(s("034f"),s("2877")),f=Object(h["a"])(m,r,i,!1,null,null,null),v=f.exports,x=s("5c96"),b=s.n(x),g=(s("0fae"),s("1c01"),s("58b2"),s("8e6e"),s("f3e2"),s("d25f"),s("57e7"),s("ac6a"),s("456d"),s("bd86")),w=s("a925"),y=s("a78e"),_=s.n(y),j=s("b2d6"),S=s.n(j),C=s("f0d9"),k=s.n(C),P={},O={};function A(e,t){var s=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),s.push.apply(s,a)}return s}function q(e){for(var t=1;t<arguments.length;t++){var s=null!=arguments[t]?arguments[t]:{};t%2?A(Object(s),!0).forEach((function(t){Object(g["a"])(e,t,s[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(s)):A(Object(s)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(s,t))}))}return e}a["default"].use(w["a"]);var U={en:q({},P,{},S.a),zh:q({},O,{},k.a)};function D(){var e=_.a.get("language");if(e)return e;for(var t=(navigator.language||navigator.browserLanguage).toLowerCase(),s=Object.keys(U),a=0,r=s;a<r.length;a++){var i=r[a];if(t.indexOf(i)>-1)return i}return"zh"}var I=new w["a"]({locale:D(),messages:U}),M=I;a["default"].config.productionTip=!1,a["default"].use(b.a,{i18n:function(e,t){return M.t(e,t)}}),new a["default"]({el:"#app",i18n:M,render:function(e){return e(v)}})},"64a9":function(e,t,s){},cf05:function(e,t,s){e.exports=s.p+"img/logo.3ba970fe.png"}});
//# sourceMappingURL=app.6661fb31.js.map