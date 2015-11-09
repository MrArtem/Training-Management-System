(function(){

	"use strict";

	angular
		.module('tmsApp')
		.directive('trePopover', trePopover);

	/* @ngInject */
	function trePopover() {
		return {
			restrict: 'E',
			translude: true,
			scope: {id: '@', popoverTemplate: '@' },
			template: '<div class="dropdown pull-left"><p class="info-small dropdown-toggle" id="{{id}}" type="button"' +
			' popover-placement="bottom" popover-trigger="click"' + 'popover-template="{{popoverTemplate}}">' +
			'<ng-translude></ng-translude></div>'
		};
	}


})();