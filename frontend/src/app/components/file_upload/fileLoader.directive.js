(function () {
    'use strict';

    angular
        .module('tmsApp')
        .directive('ngFileSelect', fileSelect);

    /** @ngInject */
    function fileSelect() {
        var fileSelect = {
            link: function($scope,el){
                el.bind("change", function(e){
                    $scope.file = (e.srcElement || e.target).files[0];
                    $scope.getFile();
                });
            }
        };

        return fileSelect;
    }
})();
