(function() {
    'use strict';

    angular
        .module('tmsApp')
        .directive('closeModal', closeModal);

    /** @ngInject*/
    function closeModal() {
        var closeModal = {
            restrict: 'A',
            scope: {
                closeModal: "@"
            },
            link: function(scope, elem, attrs) {
                $(document).foundation();
                $(elem).click(function(){
                    $('#' + scope.closeModal).foundation('reveal', 'close');
                });
            }
        };

        return closeModal;

    }
})();