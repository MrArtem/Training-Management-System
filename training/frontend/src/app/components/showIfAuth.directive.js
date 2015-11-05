(function(){

    "use strict";
    
    angular
        .module('tmsApp')
        .directive('showIfAuth', showIfAuth);
    
    /* @ngInject */
    function showIfAuth($rootScope, $state) {
        return {
            restrict: 'A',
            link: function(scope,elem, attrs){
                $rootScope.$on("$viewContentLoaded", function (event){
                    if ($state.current.name === 'login'){
                        $(elem).hide();
                    } else {
                        $(elem).show();
                    } 
                });  
            }
        };
    }
    
    
})();