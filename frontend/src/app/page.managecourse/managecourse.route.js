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
                controllerAs: 'dates',
                auth: true,
                accessRights: 2
            }).state('managecourse.files', {
                url: '/files',
                templateUrl: 'app/page.managecourse/managecourse.files/files.html',
                controller: 'FilesController',
                controllerAs: 'files',
                auth: true,
                accessRights: 2
            }).state('managecourse.dates.manual', {
                url: '/manual',
                templateUrl: 'app/page.managecourse/managecourse.dates/dates.tabs/manual.tab.html',
                auth: true,
                accessRights: 2
            }).state('managecourse.dates.repeat', {
                url: '/repeat',
                templateUrl: 'app/page.managecourse/managecourse.dates/dates.tabs/repeat.tab.html',
                auth: true,
                accessRights: 2
            })
        ;
        $urlRouterProvider.otherwise('/');
    }

})();
