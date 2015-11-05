(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('FileUploadController', FileUploadController);


    /** @ngInject */
    function FileUploadController($scope, fileReader) {
        var vm = this;
        $scope.progress = 0;
        $scope.getFile = function () {
            $scope.progress = 0;
            fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
                $scope.$parent.filesToUpload.push({
                    data: result,
                    link: "",
                    name: ($scope.file.webkitRelativePath || $scope.file.name)
                });
            });
        };
        $scope.$on("fileProgress", function (e, progress) {
            $scope.progress = progress.loaded / progress.total;
        });

        $scope.nullProgress = function () {
            $scope.progress = 0;
        };
    }

    ///** @ngInject */
    //function FileUploadController($scope, fileReader) {
    //    var vm = this;
    //    $scope.progress = 0;
    //    $scope.getFile = function () {
    //        $scope.progress = 0;
    //        fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
    //            $scope.$parent.courseInfo.files.push({
    //                data: result,
    //                link: "",
    //                name: ($scope.file.webkitRelativePath || $scope.file.name)
    //            });
    //        });
    //    };
    //    $scope.$on("fileProgress", function (e, progress) {
    //        $scope.progress = progress.loaded / progress.total;
    //    });
    //
    //    $scope.nullProgress = function () {
    //        $scope.progress = 0;
    //    };
    //}

})();