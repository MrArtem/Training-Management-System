(function () {
    'use strict';
    angular
        .module('tmsApp')
        .config(httpInterceptorConfig)

    /** @ngInject */
    function httpInterceptorConfig($httpProvider) {
        $httpProvider.interceptors.push(interceptor);
    }

    /** @ngInject */
    function interceptor($q) {
        return {
            responseError: function (rejection) {
                switch (rejection.status) {
                    case 401:
                    // Unauthorized
                    $state.go('login');
                    break;

                    case 403:
                    //Forbidden
                    $state.go('login');
                    break;
                }
                return $q.reject(rejection);
            }
        }


    }


})();