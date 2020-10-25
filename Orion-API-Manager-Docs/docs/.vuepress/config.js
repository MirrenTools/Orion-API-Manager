module.exports = {
	base: '/orion-api-manager-docs/',
	title: 'Orion-API-Manager V1.0.0',
	description: '一个API文档管理器，为后端开发人员提供API管理，也为前端人员提供友好容易查看与测试的UI,支持导入或显示OpenAPI (Swagger)等接口文档...',
	dest: 'dist',
	themeConfig: {
		nav: [{
				text: 'MirrenTools',
				link: 'https://mirrentools.org'
			},
			{
				text: 'github',
				link: 'https://github.com/MirrenTools/Orion-API-Manager'
			},
			{
				text: 'gitee',
				link: 'https://gitee.com/MirrenTools/Orion-API-Manager'
			}
		],
		sidebar: {
			'/': [{
				title: '使用说明',
				collapsable: false,
				children: ['/start/introduction']
			}]
		}
	}
}
