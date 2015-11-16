(function () {
	'use strict';

	angular
		.module('tmsApp')
		.controller('AdminController', AdminController);

	/** @ngInject */
	function AdminController($location) {
		var vm = this;
		vm.isActive = isActive;

		function isActive(state) {
			return $location.absUrl().search(state) === -1 ? false : true
		}

	}
})();