(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('courseAPI', courseAPI);

    /* @ngInject */
    function courseAPI($state, $http, urlProvider) {
        var courseAPI = {
            addComment: addComment,
            addExCoach: addExCoach, //TODO
            addLesson: addLesson,
            addParticipant: addParticipant,
            approveCourse: approveCourse,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            createCourse: createCourse,
            deleteFile: deleteFile, //TODO
            deleteLesson: deleteLesson,
            deleteParticipant: deleteParticipant,
            editCourse: editCourse,
            getAllTags: getAllTags, //TODO
            getAttachments: getAttachments,
            getComments: getComments,
            getCourseList: getCourseList,
            getEditedCourse: getEditedCourse,
            getExCoachList: getExCoachList, //TODO
            getParticipants: getParticipants,
            getShortInfo: getShortInfo,
            getTimetable: getTimetable,
            editLesson: editLesson,
            leave: leave,
            setRating: setRating,
            subscribe: subscribe,
            uploadFiles: uploadFiles
        }
        return courseAPI;

        //////////

        function getAttachments(courseId) {
            return $http.get(urlProvider.getAttachments(courseId)).then(function(result) {
                return result.data;
            });
        }

        function getComments(courseId) {
            return $http.get(urlProvider.getComments(courseId)).then(function (result) {
                return result.data;
            });
        }

        //TODO
        function getExCoachList() {
            return $http.get(urlProvider.getExCoachList()).then(function(result) {
                return result.data;
            });
        }

        function getParticipants(courseId) {
            return $http.get(urlProvider.getParticipants(courseId)).then(function (result) {
                return result.data;
            });
        }

        function getShortInfo(courseId) {
            return $http.get(urlProvider.getShortInfo(courseId)).then(function(result) {
                return result.data;
            });
        }

        function getAllTags() {
            return $http.get(urlProvider.getAllTags()).then(function(result) {
                return result.data;
            });
        }

        function getTimetable(courseId) {
            return $http.get(urlProvider.getTimetable(courseId)).then(function (result) {
                return result.data;
            });
        }

        //////////

        function approveCourse(actionId, courseData) {
            console.log(courseData);
            return $http.post(urlProvider.approveCourse(actionId), courseData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (results) {
                console.log('Course approved successfully!');
                $state.transitionTo('admin');
                return results.data;
            });
        }

        function createCourse(courseData) {
            console.log(courseData);
            return $http.post(urlProvider.createCourse(), courseData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (results) {
                console.log('Course created successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function editCourse(courseId, courseData, isDraft, justEdit) {
            var fd = new FormData();
            if (isDraft) {
                console.log("Course just created");
                if (justEdit) {
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.editCourse(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }
                else {
                    console.log("admin approves course!");
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.confirm(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }

            }
            else {
                console.log("isnt draft");
                //courseData.additional = "";
                //courseData.dateTime = [""];
                //courseData.places = [""];

                fd.append('courseInfo', JSON.stringify(courseData));

                if (justEdit) {
                    console.log(courseData);
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.editCourse(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }
                else {
                    return $http.put(urlProvider.confirm(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course approved successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });

                }
            }
        }

        function cancelCreate(courseId) {
            return $http.put(urlProvider.cancelCreate(courseId), {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).then(function (results) {
                console.log('Course canceled successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function cancelEdit(courseId) {
            return $http.put(urlProvider.cancelEdit(courseId), {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).then(function (results) {
                console.log('Course canceled successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function getEditedCourse(courseId) {
            return $http.get(urlProvider.getEditedCourse(courseId))
                .then(function (results) {
                    return results.data;
                });
        }

        //////////

        function addLesson(courseId, newDate, newPlace) {
            var lessonInfo = {
                date: newDate,
                place: newPlace
            };
            return $http.post(urlProvider.manageLesson(courseId), lessonInfo).then(function (result) {
                return result.data;
            });
        }

        function editLesson(courseId, lessonId, newDate, newPlace) {
            var lessonInfo = {
                prevLessonId: lessonId,
                date: (new Date(newDate)).getTime(),
                place: newPlace
            };
            return $http.put(urlProvider.manageLesson(courseId), lessonInfo).then(function (result) {
                return result.data;
            });
        }

        function deleteLesson(courseId, lessonId) {
            return $http.delete(urlProvider.deleteLesson(courseId, lessonId)).then(function (result) {
                return result.data;
            });
        }

        //////////

        function addParticipant(courseId, participantInfo) {
            return $http.post(urlProvider.addParticipant(courseId), participantInfo).then(function (result) {
                return result.data;
            });
        }

        function deleteParticipant(courseId, userId) {
            return $http.put(urlProvider.deleteParticipant(courseId, userId)).then(function(result) {
                return result.data;
            });
        }

        //////////

        function addComment(courseId, commentInfo) {
            return $http.post(urlProvider.addComment(courseId), commentInfo).then(function(result) {
                return result.data;
            });
        }

        function addExCoach(coachData) {
            return $http.post(urlProvider.addExCoach(), coachData).then(function(result) {
                return result.data;
            });
        }

        //////////

        function uploadFiles(files) {
            console.log(files);
            var formData = new FormData();
            for(var i in files) {
                formData.append('files', angular.toJson(files[i].data));
            }

            console.log(formData);
            return $http.post(urlProvider.uploadFiles(), formData, {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            });
        }

        //TODO
        function deleteFile(fileId) {
           //return $http.put(urlProvider.deleteFile(fileId)).then(function(result) {
           //    return result.data;
           //});
        }

        //////////

        function getCourseList(isActual, tagList) {
            return $http.get(urlProvider.getCourseList(), {
                params: {
                    is_actual: isActual,
                    page: 1,
                    tag: []
                }
            }).then(function (results) {
                return results.data;
            });
        }

        function leave(courseId) {
            return $http.put(urlProvider.leave(courseId)).then(function(result) {
                return result.data;
            });
        }

        //TODO
        function setRating(courseId, rating) {
            return $http.put(urlProvider.setRating(courseId, rating)).then(function(result) {
                return result.data;
            });
        }

        function subscribe(courseId) {
            return $http.post(urlProvider.subscribe(courseId)).then(function(result) {
                return result.data;
            });
        }

    }

})();