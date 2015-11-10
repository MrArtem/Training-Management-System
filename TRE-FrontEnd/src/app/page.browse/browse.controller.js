(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('BrowseController', BrowseController);

    /** @ngInject */
    function BrowseController($filter, courseAPI) {
        var vm = this;
        vm.courseList = [];
        vm.filteredCourses = [];
        vm.tagList = [];
        vm.selectedTags = [];
        vm.isContentLoaded = false;

        vm.getTagList = getTagList;
        vm.getTrainingList = getTrainingList;
        vm.isTagSelected = isTagSelected;
        vm.selectTag = selectTag;

        vm.getTagList();
        vm.getTrainingList();

        function getTagList() {
            courseAPI.getAllTags().then(function(data) {
                vm.tagList = angular.copy(data);
                console.log('Received tags: ', data);
            });
        }

        function getTrainingList() {
            //false stands for isActual
            courseAPI.getCourseList(false, vm.selectedTags).then(function(data) {
                vm.courseList = angular.copy(data);
                vm.isContentLoaded = true;
                console.log('Received courses: ');
                console.log(data);
            });
        }

        function isTagSelected(tagSpec) {
            return (vm.selectedTags.indexOf(tagSpec) > -1);
        }

        function selectTag(tagSpec) {
            var tagIndex = vm.selectedTags.indexOf(tagSpec);
            if(tagIndex > -1) {
                vm.selectedTags.splice(tagIndex, 1);
            }
            else {
                vm.selectedTags.push(tagSpec);
            }

            vm.getTrainingList();
            //vm.filteredCourses = $filter('filterByTags')(vm.courseList, vm.selectedTags);
        }
    }
})();