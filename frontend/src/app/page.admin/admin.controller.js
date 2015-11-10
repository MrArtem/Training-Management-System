(function () {
	'use strict';

	angular
		.module('tmsApp')
		.controller('AdminController', AdminController);

	/** @ngInject */
	function AdminController($state) {
		var vm = this;
		vm.isActive = isActive;

		function isActive(state) {
			return $state.includes(state);
		}

	}
})();