(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('AddFileUploadController', AddFileUploadController)
        .controller('FileUploadController', FileUploadController)
        .controller('UploadController', UploadController);


    /** @ngInject */
    function AddFileUploadController($scope, fileReader) {
        var vm = this;
        $scope.progress = 0;
        $scope.getFile = function () {
            $scope.progress = 0;
            fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
                $scope.filesToUpload.push({
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

    /** @ngInject */
    function FileUploadController($scope, fileReader) {
        var vm = this;
        $scope.progress = 0;
        $scope.getFile = function () {
            $scope.progress = 0;
            fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
                $scope.courseInfo.files.push({
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

    /** @ngInject */
    function UploadController($scope, fileReader) {
        var vm = this;
        $scope.progress = 0;
        $scope.getFile = function () {
            $scope.progress = 0;
            fileReader.readAsDataUrl($scope.file, $scope).then(function (result) {
                $scope.courseInfo.file.data = result;
                //$scope.courseInfo.file.link = "";
                $scope.courseInfo.file.name = ($scope.file.webkitRelativePath || $scope.file.name);
            });
        };
        $scope.$on("fileProgress", function (e, progress) {
            $scope.progress = progress.loaded / progress.total;
        });

        $scope.nullProgress = function () {
            $scope.progress = 0;
        };
    }

})();