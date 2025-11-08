import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  thresholds: {
    http_req_failed: ['rate<0.10'],
    http_req_duration: ['p(95)<5000'],
  },
  stages: [
    { duration: '30s', target: 10 },
    { duration: '30s', target: 100 },
    { duration: '30s', target: 300 },
    { duration: '30s', target: 600 },
    { duration: '30s', target: 1000 },
  ],
  gracefulStop: '30s',
  gracefulRampDown: '30s',
};

export default function () {
  const res = http.get('https://jsonplaceholder.typicode.com/posts');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1);
}
