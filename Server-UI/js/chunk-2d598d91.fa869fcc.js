(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d598d91"],{"0db5":function(e,t,a){"use strict";a.d(t,"m",(function(){return n})),a.d(t,"p",(function(){return i})),a.d(t,"w",(function(){return o})),a.d(t,"e",(function(){return l})),a.d(t,"y",(function(){return s})),a.d(t,"s",(function(){return p})),a.d(t,"r",(function(){return u})),a.d(t,"i",(function(){return c})),a.d(t,"l",(function(){return d})),a.d(t,"v",(function(){return m})),a.d(t,"t",(function(){return v})),a.d(t,"b",(function(){return h})),a.d(t,"a",(function(){return f})),a.d(t,"f",(function(){return b})),a.d(t,"k",(function(){return g})),a.d(t,"n",(function(){return w})),a.d(t,"u",(function(){return y})),a.d(t,"x",(function(){return x})),a.d(t,"d",(function(){return $})),a.d(t,"c",(function(){return _})),a.d(t,"g",(function(){return D})),a.d(t,"j",(function(){return k})),a.d(t,"o",(function(){return R})),a.d(t,"q",(function(){return S})),a.d(t,"h",(function(){return C}));var r=a("a5f4");function n(e,t){r["a"].get("/private/project").then(e).catch(t)}function i(e,t,a){r["a"].get("/private/project/"+e).then(t).catch(a)}function o(e,t,a){r["a"].post("/private/server/project/",e).then(t).catch(a)}function l(e,t,a){r["a"].post("/private/server/project/copy/"+e).then(t).catch(a)}function s(e,t,a){r["a"].put("/private/server/project/",e).then(t).catch(a)}function p(e,t,a){r["a"].put("/private/server/project/moveUp/"+e).then(t).catch(a)}function u(e,t,a){r["a"].put("/private/server/project/moveDown/"+e).then(t).catch(a)}function c(e,t,a){r["a"].delete("/private/server/project/"+e).then(t).catch(a)}function d(e,t,a){r["a"].get("/private/server/project/apiGroup/"+e).then(t).catch(a)}function m(e,t,a){r["a"].post("/private/server/apiGroup",e).then(t).catch(a)}function v(e,t,a){r["a"].put("/private/server/apiGroup",e).then(t).catch(a)}function h(e,t,a){r["a"].put("/private/server/apiGroup/moveUp/"+e).then(t).catch(a)}function f(e,t,a){r["a"].put("/private/server/apiGroup/moveDown/"+e).then(t).catch(a)}function b(e,t,a){r["a"].delete("/private/server/apiGroup/"+e).then(t).catch(a)}function g(e,t,a){r["a"].get("/private/server/apis/"+e).then(t).catch(a)}function w(e,t,a){r["a"].get("/private/server/api/"+e).then(t).catch(a)}function y(e,t,a){r["a"].post("/private/server/api",e).then(t).catch(a)}function x(e,t,a){r["a"].put("/private/server/api",e).then(t).catch(a)}function $(e,t,a){r["a"].put("/private/server/api/moveUp/"+e).then(t).catch(a)}function _(e,t,a){r["a"].put("/private/server/api/moveDown/"+e).then(t).catch(a)}function D(e,t,a){r["a"].delete("/private/server/api/"+e).then(t).catch(a)}function k(e,t){r["a"].get("/private/server/api/template").then(e).catch(t)}function R(e,t,a){r["a"].get("/private/server/api/template/"+e).then(t).catch(a)}function S(e,t,a){r["a"].post("/private/server/api/template",e).then(t).catch(a)}function C(e,t,a){r["a"].delete("/private/server/api/template/"+e).then(t).catch(a)}},"28a5":function(e,t,a){"use strict";var r=a("aae3"),n=a("cb7c"),i=a("ebd6"),o=a("0390"),l=a("9def"),s=a("5f1b"),p=a("520a"),u=a("79e5"),c=Math.min,d=[].push,m="split",v="length",h="lastIndex",f=4294967295,b=!u((function(){RegExp(f,"y")}));a("214f")("split",2,(function(e,t,a,u){var g;return g="c"=="abbc"[m](/(b)*/)[1]||4!="test"[m](/(?:)/,-1)[v]||2!="ab"[m](/(?:ab)*/)[v]||4!="."[m](/(.?)(.?)/)[v]||"."[m](/()()/)[v]>1||""[m](/.?/)[v]?function(e,t){var n=String(this);if(void 0===e&&0===t)return[];if(!r(e))return a.call(n,e,t);var i,o,l,s=[],u=(e.ignoreCase?"i":"")+(e.multiline?"m":"")+(e.unicode?"u":"")+(e.sticky?"y":""),c=0,m=void 0===t?f:t>>>0,b=new RegExp(e.source,u+"g");while(i=p.call(b,n)){if(o=b[h],o>c&&(s.push(n.slice(c,i.index)),i[v]>1&&i.index<n[v]&&d.apply(s,i.slice(1)),l=i[0][v],c=o,s[v]>=m))break;b[h]===i.index&&b[h]++}return c===n[v]?!l&&b.test("")||s.push(""):s.push(n.slice(c)),s[v]>m?s.slice(0,m):s}:"0"[m](void 0,0)[v]?function(e,t){return void 0===e&&0===t?[]:a.call(this,e,t)}:a,[function(a,r){var n=e(this),i=void 0==a?void 0:a[t];return void 0!==i?i.call(a,n,r):g.call(String(n),a,r)},function(e,t){var r=u(g,e,this,t,g!==a);if(r.done)return r.value;var p=n(e),d=String(this),m=i(p,RegExp),v=p.unicode,h=(p.ignoreCase?"i":"")+(p.multiline?"m":"")+(p.unicode?"u":"")+(b?"y":"g"),w=new m(b?p:"^(?:"+p.source+")",h),y=void 0===t?f:t>>>0;if(0===y)return[];if(0===d.length)return null===s(w,d)?[d]:[];var x=0,$=0,_=[];while($<d.length){w.lastIndex=b?$:0;var D,k=s(w,b?d:d.slice($));if(null===k||(D=c(l(w.lastIndex+(b?0:$)),d.length))===x)$=o(d,$,v);else{if(_.push(d.slice(x,$)),_.length===y)return _;for(var R=1;R<=k.length-1;R++)if(_.push(k[R]),_.length===y)return _;$=x=D}}return _.push(d.slice(x)),_}]}))},"2caf":function(e,t,a){var r=a("5ca1");r(r.S,"Array",{isArray:a("1169")})},ba04:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",{staticStyle:{width:"98%","max-width":"1240px",padding:"10px 0",margin:"auto",display:"flex","align-items":"center"}},[a("div",[a("b",[e._v(e._s(e.$t("ModifyApi")))])])]),a("div",{directives:[{name:"loading",rawName:"v-loading",value:e.dataLoading,expression:"dataLoading"}],staticStyle:{width:"98%","max-width":"1240px",margin:"0 auto 50px"}},[a("el-form",{ref:"apiForm",attrs:{"label-position":"right","label-width":"120px",model:e.api,rules:e.apiRules}},[a("el-form-item",{attrs:{label:"Method"}},[a("el-select",{attrs:{placeholder:e.$t("Select")},model:{value:e.api.method,callback:function(t){e.$set(e.api,"method",t)},expression:"api.method"}},[a("el-option",{attrs:{value:"get"}},[e._v("get")]),a("el-option",{attrs:{value:"head"}},[e._v("head")]),a("el-option",{attrs:{value:"post"}},[e._v("post")]),a("el-option",{attrs:{value:"put"}},[e._v("put")]),a("el-option",{attrs:{value:"delete"}},[e._v("delete")]),a("el-option",{attrs:{value:"options"}},[e._v("options")]),a("el-option",{attrs:{value:"patch"}},[e._v("patch")]),a("el-option",{attrs:{value:"trace"}},[e._v("trace")]),a("el-option",{attrs:{value:"connect"}},[e._v("connect")]),a("el-option",{attrs:{value:"other"}},[e._v("other")])],1),a("div",{staticStyle:{display:"inline-block","margin-left":"10px"}},[a("span",[e._v(e._s(e.$t("ApiStatus"))+":")]),a("el-radio",{attrs:{label:"false"},model:{value:e.api.deprecated,callback:function(t){e.$set(e.api,"deprecated",t)},expression:"api.deprecated"}},[e._v(e._s(e.$t("InService")))]),a("el-radio",{attrs:{label:"true"},model:{value:e.api.deprecated,callback:function(t){e.$set(e.api,"deprecated",t)},expression:"api.deprecated"}},[e._v(e._s(e.$t("DeprecatedWillDelete")))])],1)],1),a("el-form-item",{attrs:{prop:"path",label:"Path"}},[a("el-input",{attrs:{placeholder:e.$t("EnterPath")},model:{value:e.api.path,callback:function(t){e.$set(e.api,"path",t)},expression:"api.path"}})],1),a("el-form-item",{attrs:{prop:"title",label:e.$t("ApiName")}},[a("el-input",{attrs:{placeholder:e.$t("EnterApiName")},model:{value:e.api.title,callback:function(t){e.$set(e.api,"title",t)},expression:"api.title"}})],1),a("el-form-item",{attrs:{label:e.$t("ApiDescription")}},[a("el-input",{attrs:{type:"textarea",placeholder:e.$t("EnterApiDescription")},model:{value:e.api.description,callback:function(t){e.$set(e.api,"description",t)},expression:"api.description"}})],1),a("el-form-item",{attrs:{label:e.$t("Ranking")}},[a("el-input",{attrs:{type:"number",placeholder:e.$t("EnterRanking")},model:{value:e.api.sorts,callback:function(t){e.$set(e.api,"sorts",t)},expression:"api.sorts"}})],1),a("el-form-item",{attrs:{label:"Consumes"}},[a("el-input",{attrs:{placeholder:e.$t("EnterConsumes")},model:{value:e.api.consumes,callback:function(t){e.$set(e.api,"consumes",t)},expression:"api.consumes"}})],1),a("el-form-item",{attrs:{label:e.$t("Parameters")}},[a("div",[a("el-table",{staticClass:"min-height-table",staticStyle:{width:"100%"},attrs:{data:e.parameters,"row-key":"tableRowkey",border:"","default-expand-all":"","tree-props":{children:"items",hasChildren:"hasChildren"}}},[a("el-table-column",{attrs:{prop:"required",label:e.$t("Required"),width:"80",align:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-checkbox",{directives:[{name:"show",rawName:"v-show",value:1==t.row.tableRowLevel,expression:"scope.row.tableRowLevel == 1"}],staticStyle:{width:"100%"},model:{value:t.row.required,callback:function(a){e.$set(t.row,"required",a)},expression:"scope.row.required"}})]}}])}),a("el-table-column",{attrs:{prop:"in",label:e.$t("Position"),width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-select",{directives:[{name:"show",rawName:"v-show",value:1==t.row.tableRowLevel,expression:"scope.row.tableRowLevel == 1"}],attrs:{placeholder:"请选择"},model:{value:t.row.in,callback:function(a){e.$set(t.row,"in",a)},expression:"scope.row.in"}},[a("el-option",{attrs:{value:"query"}},[e._v("query")]),a("el-option",{attrs:{value:"body"}},[e._v("body")]),a("el-option",{attrs:{value:"path"}},[e._v("path")]),a("el-option",{attrs:{value:"header"}},[e._v("header")])],1)]}}])}),a("el-table-column",{attrs:{prop:"type",label:e.$t("Type"),width:"140"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-select",{staticStyle:{width:"120px"},attrs:{placeholder:e.$t("Select")},model:{value:t.row.type,callback:function(a){e.$set(t.row,"type",a)},expression:"scope.row.type"}},[a("el-option",{attrs:{value:"string"}},[e._v("string")]),a("el-option",{attrs:{value:"int"}},[e._v("int")]),a("el-option",{attrs:{value:"long"}},[e._v("long")]),a("el-option",{attrs:{value:"object"}},[e._v("object")]),a("el-option",{attrs:{value:"array"}},[e._v("array")]),a("el-option",{attrs:{value:"float"}},[e._v("float")]),a("el-option",{attrs:{value:"double"}},[e._v("double")]),a("el-option",{attrs:{value:"number"}},[e._v("number")]),a("el-option",{attrs:{value:"boolean"}},[e._v("boolean")])],1)]}}])}),a("el-table-column",{attrs:{prop:"name",label:e.$t("ParamName"),width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{attrs:{placeholder:e.$t("EnterParamName")},model:{value:t.row.name,callback:function(a){e.$set(t.row,"name",a)},expression:"scope.row.name"}})]}}])}),a("el-table-column",{attrs:{prop:"description",label:e.$t("ParamDescription")},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{staticStyle:{margin:"5px auto"},attrs:{type:"textarea",autosize:{minRows:1,maxRows:3},placeholder:e.$t("EnterParamDescription")},model:{value:t.row.description,callback:function(a){e.$set(t.row,"description",a)},expression:"scope.row.description"}})]}}])}),a("el-table-column",{attrs:{label:e.$t("Operation"),width:"65"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-popover",{attrs:{placement:"left-start",trigger:"click"}},[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.tableColumnMove(t.row,t.row.tableRowkey,0)}}},[e._v(e._s(e.$t("MoveUp")))]),a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.tableColumnMove(t.row,t.row.tableRowkey,1)}}},[e._v(e._s(e.$t("MoveDown")))]),a("el-button",{directives:[{name:"show",rawName:"v-show",value:"boolean"!=t.row.type,expression:"scope.row.type != 'boolean'"}],attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.showParameterEdit(t.row)}}},[e._v(e._s(e.$t("Edit")))]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.tableColumnRemove(t.row)}}},[e._v(e._s(e.$t("Delete")))]),a("el-button",{attrs:{slot:"reference",size:"mini",icon:"el-icon-edit"},slot:"reference"})],1)]}}])})],1),a("div",{staticStyle:{"text-align":"right",margin:"5px 0 3px"}},[a("div",{staticStyle:{"text-align":"right"}},[a("el-button",{on:{click:function(t){return e.addParameter()}}},[e._v(e._s(e.$t("AddParam")))])],1)]),a("div",[a("el-input",{attrs:{type:"textarea",autosize:{minRows:1,maxRows:10},placeholder:e.$t("EnterRequestBody")},model:{value:e.api.body,callback:function(t){e.$set(e.api,"body",t)},expression:"api.body"}})],1)],1)]),a("el-form-item",{attrs:{label:"Produces"}},[a("el-input",{attrs:{placeholder:e.$t("EnterProduces")},model:{value:e.api.produces,callback:function(t){e.$set(e.api,"produces",t)},expression:"api.produces"}})],1),a("el-form-item",{attrs:{label:e.$t("ResponsesResult")}},[a("div",[e._l(e.responses,(function(t,r){return a("div",{key:r,staticStyle:{border:"1px solid #c6e2ff",padding:"5px","margin-bottom":"5px"}},[a("div",{staticStyle:{display:"flex"}},[a("div",{staticStyle:{width:"10%","min-width":"50px","margin-right":"5px"}},[e._v(e._s(e.$t("Status"))+":")]),a("div",{staticStyle:{width:"30%","margin-right":"5px"}},[a("el-input",{attrs:{placeholder:e.$t("ResponseStatus")},model:{value:t.status,callback:function(a){e.$set(t,"status",a)},expression:"resp.status"}})],1),a("div",{staticStyle:{width:"10%","min-width":"60px","margin-right":"5px"}},[e._v(e._s(e.$t("StatusMsg"))+":")]),a("div",{staticStyle:{width:"100%"}},[a("el-input",{attrs:{placeholder:e.$t("ResponseStatusMsg")},model:{value:t.msg,callback:function(a){e.$set(t,"msg",a)},expression:"resp.msg"}})],1)]),a("div",[e._v(e._s(e.$t("Responses"))+":")]),a("el-table",{staticClass:"min-height-table",staticStyle:{width:"100%"},attrs:{data:t.data,"row-key":"tableRowkey",border:"","default-expand-all":"","tree-props":{children:"items",hasChildren:"hasChildren"}}},[a("el-table-column",{attrs:{prop:"in",label:e.$t("Position"),width:"180",align:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[1==t.row.tableRowLevel?a("el-select",{staticStyle:{width:"100px"},attrs:{placeholder:e.$t("Select")},model:{value:t.row.in,callback:function(a){e.$set(t.row,"in",a)},expression:"scope.row.in"}},[a("el-option",{attrs:{value:"body"}},[e._v("body")]),a("el-option",{attrs:{value:"header"}},[e._v("header")])],1):e._e()]}}],null,!0)}),a("el-table-column",{attrs:{prop:"tableRowLevel",label:e.$t("Level"),width:"60"}}),a("el-table-column",{attrs:{prop:"type",label:e.$t("Type"),width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-select",{staticStyle:{width:"100px"},attrs:{placeholder:e.$t("Select")},model:{value:t.row.type,callback:function(a){e.$set(t.row,"type",a)},expression:"scope.row.type"}},[a("el-option",{attrs:{value:"string"}},[e._v("string")]),a("el-option",{attrs:{value:"int"}},[e._v("int")]),a("el-option",{attrs:{value:"long"}},[e._v("long")]),a("el-option",{attrs:{value:"object"}},[e._v("object")]),a("el-option",{attrs:{value:"array"}},[e._v("array")]),a("el-option",{attrs:{value:"float"}},[e._v("float")]),a("el-option",{attrs:{value:"double"}},[e._v("double")]),a("el-option",{attrs:{value:"number"}},[e._v("number")]),a("el-option",{attrs:{value:"boolean"}},[e._v("boolean")])],1)]}}],null,!0)}),a("el-table-column",{attrs:{prop:"name",label:e.$t("ParamName"),width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{attrs:{placeholder:e.$t("EnterParamName")},model:{value:t.row.name,callback:function(a){e.$set(t.row,"name",a)},expression:"scope.row.name"}})]}}],null,!0)}),a("el-table-column",{attrs:{prop:"description",label:e.$t("ParamDescription")},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{staticStyle:{margin:"5px auto"},attrs:{type:"textarea",autosize:{minRows:1,maxRows:3},placeholder:e.$t("EnterParamDescription")},model:{value:t.row.description,callback:function(a){e.$set(t.row,"description",a)},expression:"scope.row.description"}})]}}],null,!0)}),a("el-table-column",{attrs:{label:e.$t("Operation"),width:"65"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-popover",{attrs:{placement:"left-start",trigger:"click"}},[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.tableColumnMove(t.row,t.row.tableRowkey,0)}}},[e._v(e._s(e.$t("MoveUp")))]),a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.tableColumnMove(t.row,t.row.tableRowkey,1)}}},[e._v(e._s(e.$t("MoveDown")))]),a("el-button",{directives:[{name:"show",rawName:"v-show",value:"object"==t.row.type||"array"==t.row.type,expression:"scope.row.type == 'object' || scope.row.type == 'array'"}],attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.addDataItems(t.row)}}},[e._v("\n\t\t\t\t\t\t\t\t\t\t\t"+e._s(e.$t("AddParam"))+"\n\t\t\t\t\t\t\t\t\t\t")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.tableColumnRemove(t.row)}}},[e._v(e._s(e.$t("Delete")))]),a("el-button",{attrs:{slot:"reference",size:"mini",icon:"el-icon-edit"},slot:"reference"})],1)]}}],null,!0)})],1),a("div",{staticStyle:{"text-align":"right","padding-top":"3px"}},[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(t){return e.removeResponseData(r)}}},[e._v(e._s(e.$t("Remove")))]),a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.addResponseData(t.data)}}},[e._v(e._s(e.$t("AddParam")))])],1)],1)})),a("div",{staticStyle:{"text-align":"right"}},[a("el-button",{on:{click:function(t){return e.addResponse()}}},[e._v(e._s(e.$t("AddMore")))])],1)],2)]),a("el-form-item",{attrs:{label:e.$t("ExtDocsURL")}},[a("el-input",{attrs:{placeholder:e.$t("EnterExtDocsURL")},model:{value:e.api.exDurl,callback:function(t){e.$set(e.api,"exDurl",t)},expression:"api.exDurl"}})],1),a("el-form-item",{attrs:{label:e.$t("ExtDocsDesc")}},[a("el-input",{attrs:{type:"textarea",placeholder:e.$t("EnterExtDocsDesc")},model:{value:e.api.exDdescription,callback:function(t){e.$set(e.api,"exDdescription",t)},expression:"api.exDdescription"}})],1),e.additional.length>0?a("el-form-item",{attrs:{label:e.$t("AdditionalNotes")}},e._l(e.additional,(function(t,r){return a("div",{key:r,staticStyle:{border:"1px solid #CCC",padding:"5px","margin-bottom":"5px"}},[a("el-input",{attrs:{placeholder:e.$t("AdditionalTitle")},model:{value:t.title,callback:function(a){e.$set(t,"title",a)},expression:"add.title"}}),a("el-input",{attrs:{type:"textarea",placeholder:e.$t("AdditionalDescription")},model:{value:t.description,callback:function(a){e.$set(t,"description",a)},expression:"add.description"}}),a("div",{staticStyle:{"text-align":"right","padding-top":"3px"}},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){return e.removeAdditional(r)}}},[e._v(e._s(e.$t("Remove")))])],1)],1)})),0):e._e(),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitUpdateApi()}}},[e._v(e._s(e.$t("Submit")))])],1)],1)],1),a("el-dialog",{attrs:{title:e.$t("Edit"),visible:e.dialogDataEditVisible},on:{"update:visible":function(t){e.dialogDataEditVisible=t}}},[a("el-form",{attrs:{model:e.parameterData,"label-width":"100px"}},[a("el-form-item",{attrs:{label:e.$t("Type")}},[a("el-select",{attrs:{placeholder:e.$t("Select")},model:{value:e.parameterData.type,callback:function(t){e.$set(e.parameterData,"type",t)},expression:"parameterData.type"}},[a("el-option",{attrs:{value:"string"}},[e._v("string")]),a("el-option",{attrs:{value:"int"}},[e._v("int")]),a("el-option",{attrs:{value:"long"}},[e._v("long")]),a("el-option",{attrs:{value:"object"}},[e._v("object")]),a("el-option",{attrs:{value:"array"}},[e._v("array")]),a("el-option",{attrs:{value:"float"}},[e._v("float")]),a("el-option",{attrs:{value:"double"}},[e._v("double")]),a("el-option",{attrs:{value:"number"}},[e._v("number")]),a("el-option",{attrs:{value:"boolean"}},[e._v("boolean")])],1)],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:"object"==e.parameterData.type||"array"==e.parameterData.type,expression:"parameterData.type == 'object' || parameterData.type == 'array'"}],attrs:{label:e.$t("ParamDescription")}},[a("el-table",{staticClass:"min-height-table",staticStyle:{width:"100%"},attrs:{data:e.parameterData.items,"row-key":"tableRowkey",border:"","default-expand-all":"","tree-props":{children:"items",hasChildren:"hasChildren"}}},[a("el-table-column",{attrs:{prop:"type",label:e.$t("Type"),align:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-select",{staticStyle:{width:"120px"},attrs:{placeholder:e.$t("Select")},model:{value:t.row.type,callback:function(a){e.$set(t.row,"type",a)},expression:"scope.row.type"}},[a("el-option",{attrs:{value:"string"}},[e._v("string")]),a("el-option",{attrs:{value:"int"}},[e._v("int")]),a("el-option",{attrs:{value:"long"}},[e._v("long")]),a("el-option",{attrs:{value:"object"}},[e._v("object")]),a("el-option",{attrs:{value:"array"}},[e._v("array")]),a("el-option",{attrs:{value:"float"}},[e._v("float")]),a("el-option",{attrs:{value:"double"}},[e._v("double")]),a("el-option",{attrs:{value:"number"}},[e._v("number")]),a("el-option",{attrs:{value:"boolean"}},[e._v("boolean")])],1)]}}])}),a("el-table-column",{attrs:{prop:"name",label:e.$t("ParamName")},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{attrs:{placeholder:e.$t("EnterParamName")},model:{value:t.row.name,callback:function(a){e.$set(t.row,"name",a)},expression:"scope.row.name"}})]}}])}),a("el-table-column",{attrs:{prop:"description",label:e.$t("ParamDescription")},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{staticStyle:{margin:"5px auto"},attrs:{type:"textarea",rows:1,placeholder:e.$t("EnterParamDescription")},model:{value:t.row.description,callback:function(a){e.$set(t.row,"description",a)},expression:"scope.row.description"}})]}}])}),a("el-table-column",{attrs:{label:e.$t("Operation"),width:"65"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-popover",{attrs:{placement:"left-start",trigger:"click"}},[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.tableColumnMove(t.row,t.row.tableRowkey,0)}}},[e._v(e._s(e.$t("MoveUp")))]),a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.tableColumnMove(t.row,t.row.tableRowkey,1)}}},[e._v(e._s(e.$t("MoveDown")))]),a("el-button",{directives:[{name:"show",rawName:"v-show",value:"object"==t.row.type||"array"==t.row.type,expression:"scope.row.type == 'object' || scope.row.type == 'array'"}],attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.addDataItems(t.row)}}},[e._v("\n\t\t\t\t\t\t\t\t\t"+e._s(e.$t("AddParam"))+"\n\t\t\t\t\t\t\t\t")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.tableColumnRemove(t.row)}}},[e._v(e._s(e.$t("Delete")))]),a("el-button",{attrs:{slot:"reference",size:"mini",icon:"el-icon-edit"},slot:"reference"})],1)]}}])})],1),a("div",{staticStyle:{"text-align":"right"}},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){return e.addDataItems(e.parameterData)}}},[e._v(e._s(e.$t("AddParam")))])],1)],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:"string"==e.parameterData.type,expression:"parameterData.type == 'string'"}],attrs:{label:e.$t("MaxLength")}},[a("el-input",{attrs:{placeholder:e.$t("MaxLength")},model:{value:e.parameterData.maxLength,callback:function(t){e.$set(e.parameterData,"maxLength",t)},expression:"parameterData.maxLength"}})],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:"string"==e.parameterData.type,expression:"parameterData.type == 'string'"}],attrs:{label:e.$t("MinLength")}},[a("el-input",{attrs:{placeholder:e.$t("MinLength")},model:{value:e.parameterData.minLength,callback:function(t){e.$set(e.parameterData,"minLength",t)},expression:"parameterData.minLength"}})],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:e.isNumber(e.parameterData.type),expression:"isNumber(parameterData.type)"}],attrs:{label:e.$t("Maximum")}},[a("el-input",{attrs:{placeholder:e.$t("Maximum")},model:{value:e.parameterData.maximum,callback:function(t){e.$set(e.parameterData,"maximum",t)},expression:"parameterData.maximum"}})],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:e.isNumber(e.parameterData.type),expression:"isNumber(parameterData.type)"}],attrs:{label:e.$t("Minimum")}},[a("el-input",{attrs:{placeholder:e.$t("Minimum")},model:{value:e.parameterData.minimum,callback:function(t){e.$set(e.parameterData,"minimum",t)},expression:"parameterData.minimum"}})],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:e.isCanEnumsOrRegex(e.parameterData.type),expression:"isCanEnumsOrRegex(parameterData.type)"}],attrs:{label:e.$t("Default")}},[a("el-input",{attrs:{placeholder:e.$t("Default")},model:{value:e.parameterData.def,callback:function(t){e.$set(e.parameterData,"def",t)},expression:"parameterData.def"}})],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:e.isCanEnumsOrRegex(e.parameterData.type),expression:"isCanEnumsOrRegex(parameterData.type)"}],attrs:{label:e.$t("Enums")}},[a("el-input",{attrs:{placeholder:e.$t("EnterEnums")},model:{value:e.parameterData.enums,callback:function(t){e.$set(e.parameterData,"enums",t)},expression:"parameterData.enums"}})],1),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:e.isCanEnumsOrRegex(e.parameterData.type),expression:"isCanEnumsOrRegex(parameterData.type)"}],attrs:{label:e.$t("Pattern")}},[a("el-input",{attrs:{placeholder:e.$t("Pattern")},model:{value:e.parameterData.pattern,callback:function(t){e.$set(e.parameterData,"pattern",t)},expression:"parameterData.pattern"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogDataEditVisible=!1}}},[e._v(e._s(e.$t("Cancel")))]),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogDataEditVisible=!1}}},[e._v(e._s(e.$t("Confirm")))])],1)],1)],1)},n=[],i=(a("7f7f"),a("28a5"),a("f559"),a("a481"),a("2caf"),a("0db5")),o=a("4360"),l={data:function(){return{projectId:"",groupId:"",apiId:"",dataLoading:!0,api:{method:"get"},apiRules:{path:[{required:!0,message:this.$t("EnterPath"),trigger:"blur"}],title:[{required:!0,message:this.$t("EnterApiName"),trigger:"blur"}]},parameters:[],dialogDataEditVisible:!1,parameterData:{},responses:[{status:200,msg:"ok",data:[]}],additional:[]}},created:function(){var e=o["a"].getters.role;if("ROOT"!=e&&"SERVER"!=e)this.$router.push("/index");else{if(this.projectId=this.$route.params.pid,this.groupId=this.$route.params.gid,this.apiId=this.$route.params.aid,null==this.apiId)return void this.$message.warning(this.$t("FailedToLoadTheProjectInvalidID"));this.loadApi(this.apiId)}},methods:{loadApi:function(e){var t=this;null!=e&&""!=e&&(this.dataLoading=!0,Object(i["n"])(e,(function(e){var a=e.data;if(console.log("load API..."),console.log(a),200==a.code){if(null==a.data.apiId||""==a.data.apiId)return void t.$message.error(t.$t("FailedToLoadTheProjectInvalidID"));if(null!=a.data.additional&&""!=a.data.additional&&(a.data.additional=JSON.parse(a.data.additional)),null!=a.data.externalDocs&&""!=a.data.externalDocs&&(a.data.externalDocs=JSON.parse(a.data.externalDocs),null!=a.data.externalDocs.description&&(a.data.exDdescription=a.data.externalDocs.description),null!=a.data.externalDocs.url&&(a.data.exDurl=a.data.externalDocs.url)),null!=a.data.consumes&&""!=a.data.consumes&&(a.data.consumes=JSON.parse(a.data.consumes).join(",")),null!=a.data.produces&&""!=a.data.produces&&(a.data.produces=JSON.parse(a.data.produces).join(",")),null!=a.data.parameters&&""!=a.data.parameters){for(var r=JSON.parse(a.data.parameters),n=0;n<r.length;n++)r[n].tableRowkey=t.getTableRandomRowKey(),r[n].tableRowLevel=1,r[n].required=1==r[n].required||"true"==r[n].required,r[n].ref=r,null!=r[n].items?t.recursionLoadTableData(r[n]):r[n].items=[];t.parameters=r}else t.parameters=[];if(null!=a.data.responses&&""!=a.data.responses){var i=JSON.parse(a.data.responses);i=null!=i&&i.length>0&&(null==i[0].status||null==i[0].data)?[{status:200,msg:"ok",data:i}]:i;for(n=0;n<i.length;n++)for(var o=i[n].data,l=0;l<o.length;l++){var s=o[l];s.tableRowkey=t.getTableRandomRowKey(),s.tableRowLevel=1,s.ref=o,null!=s.items?t.recursionLoadTableData(s):s.items=[]}t.responses=i}else t.responses=[];null!=a.data.additional&&""!=a.data.additional&&(Array.isArray(a.data.additional)?t.additional=a.data.additional:t.additional=JSON.parse(a.data.additional)),t.api=a.data}t.dataLoading=!1}),(function(e){t.$message.error(t.$t("FailedToGetGroupInfoSeeConsole")),console.log(e)})))},recursionLoadTableData:function(e){if(null!=e.items&&0!=e.items.length)for(var t=0;t<e.items.length;t++){var a=e.items[t];a.tableRowkey=this.getTableRandomRowKey(),a.tableRowLevel=e.tableRowLevel+1,a.ref=e.items,null!=a.items?this.recursionLoadTableData(a):a.items=[]}},submitUpdateApi:function(){var e=this;this.$refs.apiForm.validate((function(t){if(!t)return e.$message.warning(e.$t("MissingRequiredInformation")),!1;var a={};if(a.apiId=e.apiId,a.groupId=e.groupId,a.method=e.api.method,a.deprecated=e.api.deprecated,a.path=e.api.path.replace(/(\/)+/g,"/"),a.path.startsWith("/")||(a.path="/"+a.path),a.title=e.api.title,null!=e.api.description&&""!=e.api.description&&(a.description=e.api.description),isNaN(e.api.sorts)||(a.sorts=parseInt(e.api.sorts)),null!=e.api.consumes&&""!=e.api.consumes){for(var r=e.api.consumes.split(","),n=[],o=0;o<r.length;o++)","!=r[o]&&""!=r[o]&&n.push(r[o]);n.length>0&&(a.consumes=JSON.stringify(n))}if(null!=e.api.produces&&""!=e.api.produces){r=e.api.produces.split(",");var l=[];for(o=0;o<r.length;o++)","!=r[o]&&""!=r[o]&&l.push(r[o]);l.length>0&&(a.produces=JSON.stringify(l))}if(e.parameters.length>0){var s=[];for(o=0;o<e.parameters.length;o++){var p=e.parameters[o];if(null!=p.name&&""!=p.name){var u={required:p.required,in:p.in,type:p.type,name:p.name,description:p.description};if(null!=p.def&&""!=p.def&&(u.def=p.def),null!=p.minLength&&""!=p.minLength&&(u.minLength=p.minLength),null!=p.maxLength&&""!=p.maxLength&&(u.maxLength=p.maxLength),null!=p.minimum&&""!=p.minimum&&(u.minimum=p.minimum),null!=p.maximum&&""!=p.maximum&&(u.maximum=p.maximum),null!=p.enums&&""!=p.enums){r=p.enums.split(",");var c=[];for(o=0;o<r.length;o++)","!=r[o]&&""!=r[o]&&c.push(r[o]);c.length>0&&(u.enums=JSON.stringify(c))}null!=p.pattern&&""!=p.pattern&&(u.pattern=p.pattern),null!=p.items&&p.items.length>0&&(u.items=[],e.recursionConverter(u.items,p.items)),s.push(u)}}a.parameters=JSON.stringify(s)}if(null!=e.api.body&&""!=e.api.body&&(a.body=e.api.body),e.responses.length>0){for(s=[],o=0;o<e.responses.length;o++){p=e.responses[o];if(null!=p.status&&""!=p.status||null!=p.data&&0!=p.data.length){u={status:p.status,msg:p.msg,data:[]};for(var d=0;d<p.data.length;d++){var m=p.data[d];if(null!=m.name&&""!=m.name){var v={type:m.type,in:m.in,name:m.name,description:m.description};null!=m.items&&m.items.length>0&&(v.items=[],e.recursionConverter(v.items,m.items)),u.data.push(v)}}s.push(u)}}a.responses=JSON.stringify(s)}var h=null;null!=e.api.exDdescription&&(h={description:e.api.exDdescription}),null!=e.api.exDurl&&(null==h&&(h={}),h.url=e.api.exDurl),null!=h&&(a.externalDocs=JSON.stringify(h)),null!=e.additional&&e.additional.length>0&&(a.additional=JSON.stringify(e.additional)),console.log("update API"),console.log(a),Object(i["x"])(a,(function(t){var a=t.data;200==a.code&&e.$confirm(e.$t("ModifySuccessAskReturn"),e.$t("ModifySuccess"),{confirmButtonText:e.$t("GoBack"),cancelButtonText:e.$t("Cancel"),type:"success"}).then((function(){e.$router.go(-1)})).catch((function(){location.reload()}))}),(function(t){e.$message.error(e.$t("FailedToModifySeeConsole")),console.log(t)}))}))},recursionConverter:function(e,t){if(null!=t&&0!=t.length)for(var a=0;a<t.length;a++){var r={};r.type=t[a].type,r.name=t[a].name,r.description=t[a].description,null!=t[a].items&&0!=t[a].items.length&&(r.items=[],this.recursionConverter(r.items,t[a].items)),e.push(r)}},addParameter:function(){this.parameters.push({tableRowkey:this.getTableRandomRowKey(),tableRowLevel:1,required:!0,in:"query",type:"string",name:"",description:"",items:[],ref:this.parameters})},showParameterEdit:function(e){this.dialogDataEditVisible=!0,this.parameterData=e},addResponse:function(){this.responses.push({status:null,msg:null,data:[]})},addResponseData:function(e){e.push({tableRowkey:this.getTableRandomRowKey(),tableRowLevel:1,type:"string",name:"",in:"body",description:"",items:[],ref:e})},removeResponseData:function(e){var t=this;this.$confirm(this.$t("RemoveConfirm"),this.$t("Tips"),{confirmButtonText:this.$t("Confirm"),cancelButtonText:this.$t("Cancel"),type:"warning"}).then((function(){t.responses.splice(e,1)})).catch((function(){}))},showResponseDataEdit:function(e){this.dialogResponseEditVisible=!0,this.responseEditData=e,console.log(e)},addDataItems:function(e){e.items.push({tableRowkey:this.getTableRandomRowKey(),tableRowLevel:e.tableRowLevel+1,type:"string",name:"",in:"body",description:"",items:[],ref:e.items})},tableColumnMove:function(e,t,a){for(var r=-1,n=e.ref,i=0;i<n.length;i++)if(n[i].tableRowkey==t){r=i;break}if(!(-1==r||0==a&&0==r||1==a&&r==n.length-1)){var o=n[r];0==a?(this.$set(n,r,n[r-1]),this.$set(n,r-1,o)):(this.$set(n,r,n[r+1]),this.$set(n,r+1,o))}},tableColumnRemove:function(e){var t=this;this.$confirm(this.$t("RemoveConfirm"),this.$t("Tips"),{confirmButtonText:this.$t("Confirm"),cancelButtonText:this.$t("Cancel"),type:"warning"}).then((function(){t.findDataAndDelete(e.ref,e.tableRowkey)})).catch((function(){}))},findDataAndDelete:function(e,t){for(var a=0;a<e.length;a++){if(e[a].tableRowkey==t)return void e.splice(a,1);null!=e[a].items&&e[a].items.length>0&&this.findDataAndDelete(e[a].items,t)}},removeAdditional:function(e){var t=this;this.$confirm(this.$t("RemoveConfirm"),this.$t("Tips"),{confirmButtonText:this.$t("Confirm"),cancelButtonText:this.$t("Cancel"),type:"warning"}).then((function(){t.additional.splice(e,1)})).catch((function(){}))},isNumber:function(e){return"int"==e||"long"==e||"float"==e||"double"==e||"number"==e},isCanEnumsOrRegex:function(e){return"boolean"!=e&&"object"!=e&&"array"!=e},getTableRandomRowKey:function(){return"rowkey-"+Math.random()}}},s=l,p=a("2877"),u=Object(p["a"])(s,r,n,!1,null,null,null);t["default"]=u.exports}}]);
//# sourceMappingURL=chunk-2d598d91.fa869fcc.js.map