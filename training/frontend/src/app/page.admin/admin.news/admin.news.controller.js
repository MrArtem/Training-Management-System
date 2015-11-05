(function () {
	'use strict';

	angular
		.module('tmsApp')
		.controller('NewsController', NewsController);

	/** @ngInject */
	function NewsController(adminAPI) {
		var vm = this;
		vm.newsList = [];
		vm.getNews = getNews;

		vm.getNews();

		function getNews() {
			adminAPI.getNewsList().then(function(data) {
				vm.newsList = angular.copy(data);
			});
		}
	}
})();