(function () {
    'use strict';

    angular
        .module('tmsApp')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('managecourse.step1', {
                url: '/step1',
                templateUrl: 'app/page.managecourse/managecourse.step1/step1.html',
                controller: 'Step1Controller',
                controllerAs: 'step1'
            }).state('managecourse.step2', {
                url: '/step2',
                templateUrl: 'app/page.managecourse/managecourse.step2/step2.html',
                controller: 'Step2Controller',
                controllerAs: 'step2'
            }).state('managecourse.step3', {
                url: '/step3',
                templateUrl: 'app/page.managecourse/managecourse.step3/step3.html',
                controller: 'Step3Controller',
                controllerAs: 'step3'
            }).state('managecourse.step4', {
                url: '/step4',
                templateUrl: 'app/page.managecourse/managecourse.step4/step4.html',
                controller: 'Step4Controller',
                controllerAs: 'step4'
            }).state('managecourse.step4.manual', {
                templateUrl: 'app/page.managecourse/managecourse.step4/step4.tabs/manual.tab.html'
            }).state('managecourse.step4.repeat', {
                templateUrl: 'app/page.managecourse/managecourse.step4/step4.tabs/repeat.tab.html'
            })
        ;
        $urlRouterProvider.otherwise('/');
    }

})();
