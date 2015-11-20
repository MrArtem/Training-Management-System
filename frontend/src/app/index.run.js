(function () {
    'use strict';

    angular
        .module('tmsApp')
        .run(runBlock);

    /** @ngInject */
    function runBlock($log, $rootScope, $state, authService) {
        $rootScope.isLoginPage = isLoginPage;

        //try login with creds
        authService.credsLogin();

        $rootScope.$on("$stateChangeStart", function (event, toState, toParams, fromState, fromParams) {
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