(function(){

    "use strict";

    angular
        .module('tmsApp')
        .directive('positiveNumber', positiveNumber);

    /* @ngInject */
    function positiveNumber() {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function(scope,elem, attrs, ctrl){
                ctrl.$validators.positiveNumber = function(modelValue) {
                    if (ctrl.$isEmpty(modelValue)) {
                        // consider empty models to be valid
                        return true;
                    }
                    if (modelValue > 0 && modelValue === parseInt(modelValue, 10)) {
                        // it is valid
                        return true;
                    }

                    // it is invalid
                    return false;
                };
            }
        };
    }
})();