// Reference to form and elements
const buildTreeForm = document.getElementById('buildTreeForm');
const fetchTreesButton = document.getElementById('fetchTreesButton');
const treeDetails = document.getElementById('treeDetails');
const storedTrees = document.getElementById('storedTrees');

// Handle Tree Building Form Submission
buildTreeForm.addEventListener('submit', async (event) => {
  event.preventDefault();

  const inputNumbers = document.getElementById('inputNumbers').value;

  try {
    const response = await fetch('/tree/build', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(inputNumbers.split(',').map(Number)), // Convert input to array of numbers
    });

    if (!response.ok) {
      throw new Error(`Error: ${response.statusText}`);
    }

    const result = await response.json();
    treeDetails.innerHTML = `
      <p><strong>ID:</strong> ${result.id}</p>
      <p><strong>Input Numbers:</strong> ${result.inputNumbers}</p>
      <p><strong>Tree Structure:</strong> ${result.treeStructure}</p>
    `;
  } catch (error) {
    treeDetails.innerHTML = `<p style="color: red;">Failed to build tree: ${error.message}</p>`;
  }
});

// Handle Fetch Stored Trees
fetchTreesButton.addEventListener('click', async () => {
  try {
    const response = await fetch('/tree/entries', {
      method: 'GET',
    });

    if (!response.ok) {
      throw new Error(`Error: ${response.statusText}`);
    }

    const trees = await response.json();
    storedTrees.innerHTML = trees
      .map(
        (tree) => `
      <div>
        <p><strong>ID:</strong> ${tree.id}</p>
        <p><strong>Input Numbers:</strong> ${tree.inputNumbers}</p>
        <p><strong>Tree Structure:</strong> ${tree.treeStructure}</p>
        <hr>
      </div>
    `
      )
      .join('');
  } catch (error) {
    storedTrees.innerHTML = `<p style="color: red;">Failed to fetch stored trees: ${error.message}</p>`;
  }
});
