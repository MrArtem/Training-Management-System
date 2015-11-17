(function(){

    "use strict";

    angular
        .module('tmsApp')
        .directive('closeOnClick', closeOnClick);

    /* @ngInject */
    function closeOnClick() {
        return {
            restrict: 'A',
            scope: false,
            link: function (scope, elem, attrs) {
                var toggledElement = angular.element(attrs.closeOnClick);
                elem.click(function () {
                    setTimeout(function () {
                        toggledElement.trigger('click');
                    }, 0);
                });

            }
        };
    }

})();