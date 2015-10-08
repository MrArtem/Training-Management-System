(function () {
    'use strict';

    angular
        .module('tmsApp')
        .directive('datepicker', datePicker);

    /** @ngInject */
    function datePicker() {
        var datepicker = {
            require: 'ngModel',
            restrict: 'A',
            scope: {format: "="},
            link: function (scope, element, attrs, ngModel) {
                if (typeof(scope.format) == "undefined") {
                    scope.format = "yyyy-mm-dd"
                }
                $(element).fdatepicker({format: scope.format}).on('changeDate', function (ev) {
                    scope.$apply(function () {
                        ngModel.$setViewValue(ev.date);
                    });
                })
            }
        };

        return datepicker;
    }

})();
