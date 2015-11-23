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
		vm.makeText = makeText;

		vm.getNews();

		function getNews() {
			adminAPI.getNewsList().then(function(data) {
				vm.newsList = angular.copy(data);
				console.log('Received news: ', data);
			});
		}

		function makeText(type) {
			switch(type) {
				case 'ADD_FILE':
					return ' attached file to training ';
				case 'CREATE':
					return ' created training ';
				case 'EDIT':
					return ' edited training ';
				case 'JOIN':
					return ' subscribed to training ';
				case 'LEAVE':
					return ' left training ';
				case 'REMOVE':
					return ' deleted training ';
				case 'REMOVE_FILE':
					return ' removed file from training ';
				case 'SET_ATTENDANCE':
					return ' set attendance for training ';
				default:
					return 'did something to training ';
			}
		}
	}
})();