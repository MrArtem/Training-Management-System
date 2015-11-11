(function () {
    'use strict';

    angular
        .module('tmsApp')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('managecourse.dates', {
                url: '/dates',
                templateUrl: 'app/page.managecourse/managecourse.dates/dates.html',
                controller: 'DatesController',
                controllerAs: 'dates'
            }).state('managecourse.files', {
                url: '/files',
                templateUrl: 'app/page.managecourse/managecourse.files/files.html',
                controller: 'FilesController',
                controllerAs: 'files'
            }).state('managecourse.dates.manual', {
                templateUrl: 'app/page.managecourse/managecourse.dates/dates.tabs/manual.tab.html'
            }).state('managecourse.dates.repeat', {
                templateUrl: 'app/page.managecourse/managecourse.dates/dates.tabs/repeat.tab.html'
            })
        ;
        $urlRouterProvider.otherwise('/');
    }

})();
