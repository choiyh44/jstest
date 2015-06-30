(function() {
//	var app = angular.module('todoServiceModule', []);
	var app = angular.module('restApp');
	app.service('restService', ["$http", function($http){
		this.addPost = 
				function (community) {
					console.log('Starting restService.addPost.');
					var url = '/communities/111/posts/abc';
					$http.post(url, community).
						success(function(data, status, headers, config) {
							console.log(data);
							if (data && data.header && data.header.isSuccessful === true) {
							}
						});
			};

	}]);
	
})();
