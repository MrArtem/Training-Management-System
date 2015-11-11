(function () {
    'use strict';

    angular
        .module('tmsApp')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('admin.approval', {
                url: '/approval',
                templateUrl: 'app/page.admin/admin.approval/approval.html',
                controller: 'ApprovalController',
                controllerAs: 'approval'
            }).state('admin.news', {
                url: '/news',
                templateUrl: 'app/page.admin/admin.news/news.html',
                controller: 'NewsController',
                controllerAs: 'news'
            }).state('admin.statistics', {
                url: '/statistics',
                templateUrl: 'app/page.admin/admin.statistics/statistics.html',
                controller: 'StatisticsController',
                controllerAs: 'statistics'
            })
        ;

        $urlRouterProvider.otherwise('/');
    }

})();
