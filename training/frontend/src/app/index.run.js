(function () {
    'use strict';

    angular
        .module('tmsApp')
        .run(runBlock);

    /** @ngInject */
    function runBlock($log, $rootScope, $state, authService) {
        $log.debug('runBlock end');

        $rootScope.isLoginPage = isLoginPage;

        //try login with creds
        authService.credsLogin();

        $rootScope.$on("$stateChangeStart", function (event, toState, toParams, fromState, fromParams) {
            //            console.log("State: " + toState.url);
            //            console.log("Auth Rights Needed: " + toState.accessRights)
            //            console.log("Auth Rights: " + authService.getAccessRights())
            // is Logged
            if (toState.auth && !authService.isLogged()) {
                // User isn’t authenticated
                $state.go("login");
                event.preventDefault();
                return;
            }

            if (authService.isLogged) {
                if (authService.getAccessRights() > toState.accessRights) {
                    // forbidden
                    alert('Access Rights Error');
                    $state.go("login");
                    event.preventDefault();
                    return;
                }
            }
        });

        function isLoginPage() {
            return $state.includes('login');
        }
    }

})();