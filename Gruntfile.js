module.exports = function(grunt) {

  grunt.initConfig({
    // sourcePath: 'webapps/static',
    // targetPath: 'target/grunt-output',
    pkg: grunt.file.readJSON('package.json'),
    clean: {
      options: {
        'force': true
      },
      build: {
          src: ['<%= targetPath %>/*']
      }
    },

    // run: grunt concat 合并多个js文件
    concat: {
      options: {
        separator: ';',
      },
      dist: {
        src: ['webapps/static/js/login.js', 'webapps/static/js/active.js'],
        dest: 'target/dist/built.js',
      },
    },
    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      build: {
        src: 'target/dist/built.js',
        dest: 'target/<%= pkg.name %>.min.js'
      }
    },
  });

  // 加载包含 "uglify" 任务的插件。
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-concat');

  // 默认被执行的任务列表。
  grunt.registerTask('default', ['concat', 'uglify']);

};