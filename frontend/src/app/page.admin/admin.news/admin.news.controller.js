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

		function makeText(type, tableName) {
			switch(type) {
				case 'ADD_FILE':
					return ' attached file to training ';
				case 'CREATE':
					if(tableName === 'TRAINING') {
						return ' created training ';
					}
					if(tableName === 'COMMENT') {
						return ' added comment to training ';
					}
					if(tableName === 'FEEDBACK') {
						return ' added feedback from training ';
					}
					return ' created training ';
				case 'EDIT':
					if(tableName === 'TRAINING') {
						return ' edited training ';
					}
					if(tableName === 'COMMENT') {
						return ' edited comment from training ';
					}
					if(tableName === 'FEEDBACK') {
						return ' edited feedback from training ';
					}
					return ' did something to training ';
				case 'JOIN':
					return ' subscribed to training ';
				case 'LEAVE':
					return ' left training ';
				case 'REMOVE':
					if(tableName === 'TRAINING') {
						return ' deleted training ';
					}
					if(tableName === 'COMMENT') {
						return ' deleted comment from training ';
					}
					if(tableName === 'FEEDBACK') {
						return ' deleted feedback from training ';
					}
					return ' did something to training ';
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