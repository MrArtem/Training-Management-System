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
    function interceptor($q, $injector) {
        return {
            responseError: function (rejection) {
                switch (rejection.status) {
                    case 401:
                    // Unauthorized
                        $injector.get('$state').go('login');
                    break;

                    case 403:
                    //Forbidden
                        $injector.get('$state').go('login');
                    break;
                }
                return $q.reject(rejection);
            }
        }


    }


})();