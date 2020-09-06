import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Our-task'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
