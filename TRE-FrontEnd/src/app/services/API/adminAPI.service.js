(function () {
	'use strict';
	angular
		.module('tmsAPI')
		.factory('adminAPI', adminAPI);

	/* @ngInject */
	function adminAPI() {
		var adminAPI = {
			getApproveList: getApproveList,
			getNewsList: getNewsList
		}
		return adminAPI;

		function getApproveList() {
			return $http.get(urlProvider.getApproveList()).then(function (results) {
				return results.data;
			});
		}

		function getNewsList() {
			return $http.get(urlProvider.getNewsList()).then(function (results) {
				return results.data;
			});
		}

	}

})();