'use strict';
angular.module('routingModule', ['ngRoute'])

        .config('$routeProvider', function ($routeProvider) {
            $routeProvider
                    .when('/', {
                        templateUrl: 'home.html'
                    })
                    .when('/search', {
                        templateUrl: 'search.html'
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
        });

