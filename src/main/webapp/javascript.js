'use strict';
angular.module('app', [])

        .controller('httpController', ['$scope', '$http', function ($scope, $http) {

//                var self = this;
                //$scope.flightsFromDate;
                $scope.airlineInfo;

                $http.get('http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-20T00:00:00.000Z/1').then(function (response) {
                    $scope.airlineInfo = response.data;
//                    $scope.flightsFromDate = response.data.flights;

                }, function (error) {


                });

            }])
        .filter('dateFilter', function () {
            return function (date) {
                var dateSplit = date.split("T");
                return dateSplit[0];
            };
        })
        .filter('timeFilter', function () {
            return function (date) {
                var dateSplit = date.split("T");
                var timeSplit = dateSplit[1].split(":");
                var time = timeSplit[0] + ":" + timeSplit[1];
                return time;
            };
        })
        .filter('durationFilter', function () {
            return function (time) {
                return (time/60|0)+"h "+ time%60+"m";
            };
        })
        .filter('arivalFilter', function () {
            return function (time) {
                return time;
            };
        });

/*
 .factory('httpFactory', ['$http', function ($http) {
 
 var httpFactory = [];
 
 httpFactory.getFlights = function () {
 var url = 'http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-20/1';
 console.log(url);
 return $http.get(url);
 };
 
 return httpFactory;
 
 }]);
 */