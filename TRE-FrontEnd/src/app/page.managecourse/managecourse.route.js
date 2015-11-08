(function () {
    'use strict';

    angular
        .module('tmsApp')
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('step1', {
                parent: 'managecourse',
                url: '/step1',
                templateUrl: 'app/page.managecourse/managecourse.step1/step1.html',
                controller: 'Step1Controller',
                controllerAs: 'step1'
            }).state('step2', {
                parent: 'managecourse',
                url: '/step2',
                templateUrl: 'app/page.managecourse/managecourse.step2/step2.html',
                controller: 'Step2Controller',
                controllerAs: 'step2'
            }).state('step3', {
                parent: 'managecourse',
                url: '/step3',
                templateUrl: 'app/page.managecourse/managecourse.step3/step3.html',
                controller: 'Step3Controller',
                controllerAs: 'step3'
            }).state('step4', {
                parent: 'managecourse',
                url: '/step4',
                templateUrl: 'app/page.managecourse/managecourse.step4/step4.html',
                controller: 'Step4Controller',
                controllerAs: 'step4'
            }).state('step4.manual', {
                templateUrl: 'app/page.managecourse/managecourse.step4/step4.tabs/manual.tab.html'
            }).state('step4.repeat', {
                templateUrl: 'app/page.managecourse/managecourse.step4/step4.tabs/repeat.tab.html'
            })
        ;
        $urlRouterProvider.otherwise('/');
    }

})();
